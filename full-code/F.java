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

public class F {

	public static void main(String[] args) {

		Graphium g = GraphiumLoader.open(args[0]);

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
		// the result set where published at ESWC and
		// have more than 4 co-authors
		int inFinal = 0;
		for (Vertex p : result) {
			it = p.getEdgesOut();
			boolean inESWC = false;
			int authors = 0;
			while (it.hasNext()) {
				rel = it.next();
				temp = rel.getEnd();
				URI relType = rel.getURI();
				if (relType.equals("http://data.semanticweb.org/ns/swc/ontology#isPartOf")
					&& temp.isURI()
					&& temp.getURI().equals("http://data.semanticweb.org/conference/eswc/2012"))
					inESWC = true;
				else if (relType.equals("http://swrc.ontoware.org/ontology#author"))
					authors++;
			}
			it.close();
			// What is the condition that the paper in Vertex p must
			// fulfill to be acounted as part of the final result set?
			if (inESWC && authors<=4)
				inFinal++;
		}

		// Print the result
		System.out.println(inFinal);
		
		g.close();
	}
}
