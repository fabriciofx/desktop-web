package com.github.fabriciofx.dw.web;

import java.io.IOException;

import org.takes.Response;
import org.takes.facets.fork.RqRegex;
import org.takes.facets.fork.TkRegex;
import org.takes.rs.RsHtml;

public final class TkPage implements TkRegex {
	@Override
	public Response act(final RqRegex req) throws IOException {
        return new RsHtml(
        	TkPage.class.getClassLoader()
				.getResourceAsStream(
					String.format(
						"webapp/%s",
						req.matcher().group("path")
				)
			)
        );
	}
}
