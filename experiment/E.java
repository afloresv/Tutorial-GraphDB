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

public class E {

	public static void main(String[] args) {

		GraphRDF g = GraphiumLoader.open(args[0]);

		Vertex v, temp;
		Edge rel;
		GraphIterator<Edge> it;

		HashSet<Vertex> papers_ps = new HashSet<Vertex>();
		HashSet<Vertex> papers_cited_ps = new HashSet<Vertex>();
		HashSet<Vertex> result = new HashSet<Vertex>();

		v = g.getVertexURI("http://data.semanticweb.org/person/peter-smith");
		if (v == null) return;

		// Get papers written by Peter Smith in 'papers_ps'
		it = v.getEdgesIn();
		while (it.hasNext()) {
			rel = it.next();
			if (rel.getURI().equals("http://swrc.ontoware.org/ontology#author")) {
				papers_ps.add(rel.getStart());
			}
		}
		it.close();

		// Get papers cited by -papers written by- Peter Smith in 'papers_cited_ps'
		for (Vertex p : papers_ps) {
			it = p.getEdgesOut();
			while (it.hasNext()) {
				rel = it.next();
				if (rel.getURI().equals("http://swrc.ontoware.org/ontology#biblioReference")) {
					temp = rel.getEnd();
					papers_cited_ps.add(temp);
					result.add(temp);
				}
			}
			it.close();
		}

		// Get papers cited by -papers written by- Peter Smith in 'papers_cited_ps'
		for (Vertex p : papers_cited_ps) {
			it = p.getEdgesOut();
			while (it.hasNext()) {
				rel = it.next();
				if (rel.getURI().equals("http://swrc.ontoware.org/ontology#biblioReference"))
					result.add(rel.getEnd());
			}
			it.close();
		}

		// Now lets find out how many of the papers in
		// the result set where published at ESWC
		int inESWC = 0;
		for (Vertex p : result) {
			it = p.getEdgesOut();
			while (it.hasNext()) {
				rel = it.next();
				temp = rel.getEnd();
				// HINT: Which URI is used for relating a paper with a conference?
				// Check the publications.nt file to get the answer
				if (rel.getURI().equals(/* INSERT String HERE */)
					&& temp.isURI()
					&& temp.getURI().equals("http://data.semanticweb.org/conference/eswc/2012"))
					inESWC++;
			}
			it.close();
		}

		// Print the result
		System.out.println(inESWC);
		
		g.close();
	}
}
