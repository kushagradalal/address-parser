package com.kushagra.addressparser.service;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kushagra.addressparser.model.Address;
import com.kushagra.addressparser.util.CSVUtil;

@Service
public class ParserServiceImpl implements ParserService {

	private Pattern patternZip = Pattern.compile("[0-9]{5}(?:-[0-9]{4})?$");
	private Pattern patternStreet = Pattern.compile(
			"\\d+[ ](?:[A-Za-z0-9.-]+[ ]?)+(?:Avenue|Lane|Road|Boulevard|Drive|Street|Ave|Dr|Rd|Blvd|Ln|St)\\.?");
	private String boundary = "\\b";

	@Autowired
	private CSVUtil csvUtil;

	@Override
	public String getStreet(String address) {
		Matcher matcher = patternStreet.matcher(address);
		if (matcher.find()) {
			return matcher.group();
		}
		return null;
	}

	@Override
	public String getCity(String address, String stateCode) {
		if (stateCode == null) {
			return null;
		}
		Map<String, Set<String>> stateCities = csvUtil.getStateCities();
		Set<String> cities = stateCities.get(stateCode);
		Optional<String> opt = cities.stream().filter(p -> {
			Pattern pattern = Pattern.compile(boundary + p + boundary, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(address);
			return matcher.find();
		}).findFirst();
		return opt.isPresent() ? opt.get() : null;
	}

	@Override
	public String getState(String address) {
		Set<String> stateShort = csvUtil.getStateShort();
		Optional<String> opt = stateShort.stream().filter(p -> {
			Pattern pattern = Pattern.compile(boundary + p + boundary, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(address);
			return matcher.find();
		}).findFirst();
		return opt.isPresent() ? opt.get() : null;
	}

	@Override
	public String getZipCode(String address) {
		Matcher matcher = patternZip.matcher(address);
		if (matcher.find()) {
			return matcher.group();
		}
		return null;
	}

	@Override
	public Address getAddress(String address) {
		Address addr = new Address();
		addr.setStateCode(getState(address));
		addr.setCity(getCity(address, addr.getStateCode()));
		addr.setZipCode(getZipCode(address));
		addr.setStreet(getStreet(address));
		return addr;
	}

}
