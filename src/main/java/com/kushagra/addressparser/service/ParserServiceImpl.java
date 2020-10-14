package com.kushagra.addressparser.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.kushagra.addressparser.model.Address;

public class ParserServiceImpl implements ParserService {

	private static final Pattern PATTERN_ZIP = Pattern.compile("[0-9]{5}(?:-[0-9]{4})?$");
	private static final Pattern PATTERN_STREET = Pattern.compile(
			"\\d+[ ](?:[A-Za-z0-9.-]+[ ]?)+(?:Avenue|Lane|Road|Boulevard|Drive|Street|Ave|Dr|Rd|Blvd|Ln|St)\\.?");
	private static final String BOUNDARY = "\\b";
	private static final Pattern PATTERN_POBOX = Pattern
			.compile("(?:Post(?:al)? (?:Office )?|P[. ]?O\\.? )?Box [0-9]+\\b");
	private static final String PATTERN_NUMBER = "[^0-9]";

	private CSVUtil csvUtil = CSVUtil.getInstance();

	@Override
	public String getStreet(String address) {
		Matcher matcher = PATTERN_STREET.matcher(address);
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
		List<String> opt = cities.stream().filter(p -> {
			Pattern pattern = Pattern.compile(BOUNDARY + p + BOUNDARY, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(address);
			return matcher.find();
		}).collect(Collectors.toList());
		return opt.isEmpty() ? null : opt.get(opt.size() - 1);
	}

	@Override
	public String getStateCode(String address) {
		Map<String, String> states = csvUtil.getStates();
		List<String> opt = states.keySet().stream().filter(p -> {
			Pattern pattern = Pattern.compile(BOUNDARY + p + BOUNDARY, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(address);
			return matcher.find();
		}).collect(Collectors.toList());
		return opt.isEmpty() ? null : opt.get(opt.size() - 1);
	}

	@Override
	public String getState(String stateCode) {
		return csvUtil.getStates().get(stateCode);
	}

	@Override
	public String getZipCode(String address) {
		Matcher matcher = PATTERN_ZIP.matcher(address);
		if (matcher.find()) {
			return matcher.group();
		}
		return null;
	}

	@Override
	public String getPoBox(String address) {
		Matcher matcher = PATTERN_POBOX.matcher(address);
		if (matcher.find()) {
			return matcher.group().replaceAll(PATTERN_NUMBER, "");
		}
		return null;
	}

	@Override
	public Address getAddress(String address) {
		Address obj = new Address();
		if (address == null)
			return obj;
		obj.setStateCode(getStateCode(address));
		obj.setState(getState(obj.getStateCode()));
		obj.setCity(getCity(address, obj.getStateCode()));
		obj.setZipCode(getZipCode(address));
		obj.setStreet(getStreet(address));
		obj.setPoBox(getPoBox(address));
		return obj;
	}

}
