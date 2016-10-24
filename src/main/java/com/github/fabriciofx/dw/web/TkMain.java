package com.github.fabriciofx.dw.web;

import org.takes.facets.fork.FkRegex;
import org.takes.facets.fork.TkFork;
import org.takes.tk.TkWrap;

public final class TkMain extends TkWrap {
	public TkMain() {
		super(
			new TkFork(
				new FkRegex("/robots.txt", ""),
				new FkRegex("/", new TkIndex())
			)
		);
	}		
}

