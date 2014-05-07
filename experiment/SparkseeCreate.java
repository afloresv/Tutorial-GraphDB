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

public class SparkseeCreate {

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
			(new File(args[0])).mkdirs();
			db = sparksee.create(args[0]+"/SparkseeDB.gdb","SparkseeBD");
			sess = db.newSession();
			g = sess.getGraph();

			NodeType = g.newNodeType("Node");
			AttrType = g.newAttribute(NodeType,"id",DataType.String,AttributeKind.Unique);

			long paper1 = addNode("Paper1");
			long paper2 = addNode("Paper2");
			long paper3 = addNode("Paper3");
			long paper4 = addNode("Paper4");
			long paper5 = addNode("Paper5");

			long eswc = addNode("ESWC");
			long iswc = addNode("ISWC");

			long peter_smith = addNode("Peter Smith");
			long john_smith  = addNode("John Smith");
			long juan_perez  = addNode("Juan Perez");

			int conference, author, cites;

			conference = g.newRestrictedEdgeType("conference",NodeType,NodeType,true);
			addRelationship(paper1,conference,eswc);
			addRelationship(paper2,conference,iswc);
			addRelationship(paper3,conference,eswc);
			addRelationship(paper4,conference,eswc);
			addRelationship(paper5,conference,iswc);

			author = g.newRestrictedEdgeType("author",NodeType,NodeType,true);
			addRelationship(paper1,author,peter_smith);
			addRelationship(paper2,author,john_smith);
			addRelationship(paper3,author,peter_smith);
			addRelationship(paper3,author,juan_perez);
			addRelationship(paper4,author,john_smith);
			addRelationship(paper4,author,juan_perez);
			addRelationship(paper5,author,peter_smith);
			
			cites = g.newRestrictedEdgeType("cites",NodeType,NodeType,true);
			addRelationship(paper1,cites,paper2);
			addRelationship(paper3,cites,paper1);
			addRelationship(paper3,cites,paper4);
			addRelationship(paper4,cites,paper2);

		} catch (FileNotFoundException e) {
			System.err.println("Error: " + e.getMessage());
		} finally {
			sess.close();
			db.close();
			sparksee.close();
		}
	}

	public static long addNode(String id) {
		long n = g.newNode(NodeType);
		g.setAttribute(n,AttrType,val.setString(id));
		return n;
	}

	public static void addRelationship(long src, int rel, long dst) {
		g.newEdge(rel,src,dst);
	}
}
