package com.github.fabriciofx.dw.web;

import java.io.IOException;

import org.takes.Request;
import org.takes.Response;
import org.takes.Take;
import org.takes.facets.flash.RsFlash;
import org.takes.facets.forward.RsForward;
import org.takes.rq.form.RqFormBase;

public final class TkForm implements Take {
	@Override
	public Response act(final Request req) throws IOException {
		final Iterable<String> nomes = new RqFormBase(req).param("nome");
		for (final String nome: nomes) {
			System.out.println("Nome: " + nome);
		}
		return new RsForward(
			new RsFlash(
				"Obrigado por responder!"
			),
			"/"
		);
	}
}
