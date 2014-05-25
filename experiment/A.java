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

public class A {

	public static void main(String[] args) {

		Graphium g = GraphiumLoader.open(args[0]);

		Vertex v;
		Edge rel;
		GraphIterator<Edge> it;

		// Find the URI that represents 'Peter Smith'
		// HINT: Check the publications.nt file
		v = g.getVertexURI(/* INSERT String HERE */);
		if (v == null) return;

		it = v.getEdgesIn();
		while (it.hasNext()) {
			rel = it.next();
			if (rel.getURI().equals("http://swrc.ontoware.org/ontology#author")) {
				System.out.println(rel./* INSERT CODE HERE */.getAny());
				// HINT: For the Graphium API you can use 'getStart()'
				// or 'getEnd()' for getting both sides of any edge.
				// Which one do you need?
			}
		}
		it.close();
		
		g.close();
	}
}
