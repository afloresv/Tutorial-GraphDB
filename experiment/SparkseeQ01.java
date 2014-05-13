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

import com.sparsity.sparksee.gdb.*;

public class SparkseeQ01 {

	public static SparkseeConfig cfg;
	public static Sparksee sparksee;
	public static Database db;
	public static Session sess;
	public static Graph g;
	public static int NodeType, AttrType;
	public static Value val = new Value();

	public static void main(String[] args) {

		try {
			SparkseeProperties.load("conf/sparksee.cfg");
			cfg = new SparkseeConfig();
			sparksee = new Sparksee(cfg);
			db = sparksee.open(args[0]+"/SparkseeDB.gdb", true);
			sess = db.newSession();
			g = sess.getGraph();

			NodeType = g.findType("Node");
			AttrType = g.findAttribute(NodeType,"id");

			long v;
			Objects obj;
			ObjectsIterator it;

			// Find the node for 'Peter Smith'
			v = g.findObject(AttrType,val.setString(/* INSERT String HERE */));
			if (v==Objects.InvalidOID) return;

			int author = g.findType("author");
			obj = g.neighbors(v,author,EdgesDirection.Ingoing);
			it = obj.iterator();
			while (it.hasNext()) {
				System.out.println(g.getAttribute(it.next(),AttrType).getString());
			}
			// INSERT CODE HERE
			// In Sparksee remember to always close any 'Objects'
			// or 'ObjectsIterator' after you finish using it

		} catch (FileNotFoundException e) {
			System.err.println("Error: " + e.getMessage());
		} finally {
			sess.close();
			db.close();
			sparksee.close();
		}
	}
}
