/*
 *  Copyright (C) 2014, Universidad Simon Bolivar
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package experiment;

import java.util.*;
import java.lang.*;
import java.io.*;

import ve.usb.ldc.graphium.core.*;

public class B {

	public static void main(String[] args) {

		Graphium g = GraphiumLoader.open(args[0]);

		Vertex v;
		Edge rel;
		GraphIterator<Edge> it;

		// Use any container class to contain a Vertex set
		HashSet<Vertex> papers_ps = new HashSet<Vertex>();
		HashSet<Vertex> papers_cited_ps = new HashSet<Vertex>();

		v = g.getVertexURI("http://data.semanticweb.org/person/peter-smith");
		if (v == null) return;

		// First we get the Nodes of papers written by Peter Smith in 'papers_ps'
		it = v.getEdgesIn();
		while (it.hasNext()) {
			rel = it.next();
			if (rel.getURI().equals("http://swrc.ontoware.org/ontology#author")) {
				papers_ps.add(rel.getStart());
			}
		}
		it.close();

		for (Vertex p : papers_ps) {
			it = p.getEdgesOut();
			while (it.hasNext()) {
				rel = it.next();
				if (rel.getURI().equals("http://swrc.ontoware.org/ontology#biblioReference")) {
					papers_cited_ps.add(rel.getEnd());
				}
			}
			it.close();
		}

		for (Vertex p : papers_cited_ps) {
			int cited = 0;
			it = p.getEdgesIn();
			while (it.hasNext()) {
				rel = it.next();
				if (rel.getURI().equals("http://swrc.ontoware.org/ontology#biblioReference"))
					cited++;
			}
			it.close();

			if (cited<=2)
				System.out.println(p.getAny());
		}
		
		g.close();
	}
}
