package com.kushagra.addressparser.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.kushagra.addressparser.model.InputCSV;

@Component
public class CSVUtil {

	private static final String FILE_NAME = "/us_cities_states_counties.csv";
	private List<InputCSV> csv = new ArrayList<>();
	private Set<String> stateShort = new HashSet<>();
	private Set<String> stateFull = new HashSet<>();
	private Map<String, Set<String>> stateCities = new HashMap<>();

	@PostConstruct
	private void init() {
		convertCSVToPOJO();
		extractStates();
		extractStateCities();
		System.out.println(stateShort);
	}

	private void convertCSVToPOJO() {
		// get file from resource folder
		// read csv file
		try (InputStream input = getClass().getResourceAsStream(FILE_NAME);
				BufferedReader in = new BufferedReader(new InputStreamReader(input))) {

			// regex to split line by |
			Pattern pattern = Pattern.compile("\\|");

			// convert csv to POJO
			csv = in.lines().skip(1).map(line -> {
				String[] x = pattern.split(line);
				try {
					return new InputCSV(x[0], x[1], x[2], x[3], x[4]);
				} catch (ArrayIndexOutOfBoundsException e) {
					e.printStackTrace();
					return new InputCSV();
				}
			}).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void extractStates() {
		stateShort = csv.stream().map(InputCSV::getStateShort).collect(Collectors.toSet());
		stateFull = csv.stream().map(InputCSV::getStateFull).collect(Collectors.toSet());
	}

	private void extractStateCities() {
		stateCities.putAll(csv.stream().collect(Collectors.groupingBy(InputCSV::getStateShort,
				Collectors.mapping(InputCSV::getCityAlias, Collectors.toSet()))));
		stateCities.putAll(csv.stream().collect(Collectors.groupingBy(InputCSV::getStateFull,
				Collectors.mapping(InputCSV::getCityAlias, Collectors.toSet()))));
	}

	public Set<String> getStateShort() {
		return stateShort;
	}

	public Set<String> getStateFull() {
		return stateFull;
	}

	public Map<String, Set<String>> getStateCities() {
		return stateCities;
	}

}
