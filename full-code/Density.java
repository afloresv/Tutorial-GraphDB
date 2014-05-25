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

public class Density {

	public static void main(String[] args) {

		Graphium g = GraphiumLoader.open(args[0]);

		Vertex v;
		Edge e;
		GraphIterator<Vertex> it;
		GraphIterator<Edge> ite;
		int n_Nodes = 0,
			n_Edges = 0;

		it = g.getAllVertex();
		while (it.hasNext()) {
			v = it.next();
			n_Nodes++;

			ite = v.getEdgesOut();
			while (ite.hasNext()) {
				e = ite.next();
				n_Edges++;
			}
			ite.close();
		}
		it.close();

		g.close();

		// Given a graph G = (V,E)
		//                   |E|
		// Density(G) = --------------
		//               |V| * (|V|-1)
		System.out.println(((double)n_Edges)/((double)n_Nodes*(n_Nodes-1)));
	}
}
