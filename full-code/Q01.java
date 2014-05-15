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

public class Q01 {

	public static void main(String[] args) {

		GraphRDF g = GraphiumLoader.open(args[0]);

		Vertex v;
		Edge rel;
		GraphIterator<Edge> it;

		// Find the node for 'Peter Smith'
		// This time, is represented as an URI
		v = g.getVertexURI("http://data.semanticweb.org/person/peter-smith");
		if (v == null) return;

		it = v.getEdgesIn();
		while (it.hasNext()) {
			rel = it.next();
			if (rel.getURI().equals("http://swrc.ontoware.org/ontology#author")) {
				System.out.println(rel.getStart().getAny());
			}
		}
		it.close();
		
		g.close();
	}
}
