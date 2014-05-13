package jia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import env.Percept;
import model.graph.Graph;
import model.graph.Vertex;
import arch.MartianArch;
import arch.WorldModel;
import jason.asSemantics.DefaultInternalAction;
import jason.asSemantics.TransitionSystem;
import jason.asSemantics.Unifier;
import jason.asSyntax.ASSyntax;
import jason.asSyntax.Atom;
import jason.asSyntax.StringTerm;
import jason.asSyntax.Term;

/**
 * Returns true or false to indicate if the specified position was probed or not.
 * </p>
 * Use: jia.is_probed_vertex(+P); </br>
 * Where: P is the position.
 * 
 * @author mafranko
 */
public class has_not_probed_neighbor extends DefaultInternalAction {

	private static final long serialVersionUID = 1178501089234439800L;

	@Override
	public Object execute(TransitionSystem ts, Unifier un, Term[] terms) throws Exception {
		WorldModel model = ((MartianArch) ts.getUserAgArch()).getModel();
		Graph graph = model.getGraph();
		
		Vertex myPosition = model.getMyVertex();
		
		List<Vertex> neighborsList = new ArrayList<Vertex>();
		neighborsList.addAll(myPosition.getNeighbors());
		Collections.shuffle(neighborsList);
		for (Vertex neighbor : neighborsList) {
			if (!graph.isProbedVertex(neighbor.getId())) {
				String vertex = Percept.VERTEX_PREFIX + neighbor.getId();
				return un.unifies(terms[0], ASSyntax.createString(vertex));
			}
		}

		return false;
	}

}
