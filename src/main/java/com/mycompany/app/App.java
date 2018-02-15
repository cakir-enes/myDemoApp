package com.mycompany.app;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

public class App {

	public static String isStringStrong(ArrayList<Integer> arr1, ArrayList<Integer> arr2, ArrayList<Integer> arr3,
			String str) {

		if (arr1 == null || arr2 == null || arr3 == null || str == null || arr1.isEmpty() || arr2.isEmpty()
				|| arr3.isEmpty() || str.isEmpty()) {
			return "All forms must be filled!";
		}

		int[] max = new int[3];
		max[0] = findMaxOfList(arr1);
		max[1] = findMaxOfList(arr2);
		max[2] = findMaxOfList(arr3);

		int maxOfForms = max[0];

		for (int elm : max) {
			if (elm > maxOfForms)
				maxOfForms = elm;
		}

		int value = 0;

		for (int i = 0; i < str.length(); i++) {
			value += (int) str.charAt(i);
		}

		return value > maxOfForms ? "Strong string!" : "Weak string!";
	}

	public static int findMaxOfList(ArrayList<Integer> arr) {

		int max = arr.get(0);
		for (int elm : arr) {
			if (elm > max)
				max = elm;
		}
		return max;
	}

	public static void main(String[] args) {

		port(getHerokuAssignedPort());

		get("/", (req, res) -> "Hello, World");

		post("/compute", (req, res) -> {
			String in1 = req.queryParams("input1");
			java.util.Scanner sc1 = new Scanner(in1);
			sc1.useDelimiter("[;\r\n]+");
			ArrayList<Integer> inList = new ArrayList<>();
			while (sc1.hasNext()) {
				int val = Integer.parseInt(sc1.next().replaceAll("\\s", ""));
				inList.add(val);
			}

			// System.out.println(inList);

			String in2 = req.queryParams("input2");
			java.util.Scanner sc2 = new Scanner(in2);
			sc2.useDelimiter("[;\r\n]+");
			ArrayList<Integer> inList2 = new ArrayList<>();
			while (sc2.hasNext()) {
				int val = Integer.parseInt(sc2.next().replaceAll("\\s", ""));
				inList2.add(val);
			}

			// System.out.println(inList2);

			String in3 = req.queryParams("input3");
			java.util.Scanner sc3 = new Scanner(in3);
			sc3.useDelimiter("[;\r\n]+");
			ArrayList<Integer> inList3 = new ArrayList<>();
			while (sc3.hasNext()) {
				int val = Integer.parseInt(sc3.next().replaceAll("\\s", ""));
				inList3.add(val);
			}

			// System.out.println(inList3);

			String in4 = req.queryParams("input4");

			// System.out.println(in4);

			Map map = new HashMap<>();

			String result = isStringStrong(inList, inList2, inList3, in4);

			// System.out.println("\nResult: " + result);

			map.put("result", result);

			return new ModelAndView(map, "compute.mustache");
		}, new MustacheTemplateEngine());

		get("/compute", (rq, rs) -> {
			Map map = new HashMap();
			map.put("result", "Fill the forms according to How to Use section above.");
			return new ModelAndView(map, "compute.mustache");
		}, new MustacheTemplateEngine());
	}

	static int getHerokuAssignedPort() {
		ProcessBuilder processBuilder = new ProcessBuilder();
		if (processBuilder.environment().get("PORT") != null) {
			return Integer.parseInt(processBuilder.environment().get("PORT"));
		}
		return 4567;
	}

}