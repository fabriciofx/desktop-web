package com.github.fabriciofx.dw.web;

import org.takes.facets.fork.FkRegex;
import org.takes.facets.fork.TkFork;
import org.takes.tk.TkClasspath;
import org.takes.tk.TkWithType;
import org.takes.tk.TkWrap;

public final class TkRoutes extends TkWrap {
	public TkRoutes() {
		super(
			new TkFork(
				new FkRegex("/robots.txt", ""),
				new FkRegex("/css/.+\\.css",
					new TkWithType(
						new TkClasspath("/webapp"),
						"text/css"
					)
				),
				new FkRegex("/", new TkIndex()),
				new FkRegex("/form", new TkForm()),
				new FkRegex("/(?<path>[^/]+)", new TkPage())		
			)
		);
	}		
}

