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

	public static boolean search(ArrayList<Integer> array, int e) {
		System.out.println("inside search");
		if (array == null)
			return false;

		for (int elt : array) {
			if (elt == e)
				return true;
		}
		return false;
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
			System.out.println(inList);

			String in2 = req.queryParams("input2").replaceAll("\\s", "");
			int in2AsInt = Integer.parseInt(in2);

			boolean result = App.search(inList, in2AsInt);

			Map map = new HashMap<>();
			map.put("result", result);
			return new ModelAndView(map, "compute.mustache");
		}, new MustacheTemplateEngine());

		get("/compute", (rq, rs) -> {
			Map map = new HashMap();
			map.put("result", "not computed yet!");
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