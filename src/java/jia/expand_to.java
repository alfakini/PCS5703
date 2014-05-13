package jia;

import jason.asSemantics.DefaultInternalAction;
import jason.asSemantics.TransitionSystem;
import jason.asSemantics.Unifier;
import jason.asSyntax.ASSyntax;
import jason.asSyntax.Term;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import model.graph.Graph;
import model.graph.Vertex;
import model.graph.VertexComparator;
import arch.MartianArch;
import arch.WorldModel;
import env.Percept;

/**
 * Retrieves the position of a not probed neighbor if there is at least one,
 * or the position of the least visited neighbor.
 * </p>
 * Use: jia.move_to_not_probed(+P,-N); </br>
 * Where: P is the position and N is the neighbor.
 * 
 * @author mafranko
 */
public class expand_to extends DefaultInternalAction {

	private static final long serialVersionUID = -5827900602735423794L;

	private static final VertexComparator comparator = new VertexComparator();
	
	@Override
	public Object execute(TransitionSystem ts, Unifier un, Term[] terms) throws Exception {
		WorldModel model = ((MartianArch) ts.getUserAgArch()).getModel();
		Vertex myPosition = model.getMyVertex();
		Set<Vertex> neighbors = myPosition.getNeighbors();
		
		List<Vertex> neighborsList = new ArrayList<Vertex>();
		neighborsList.addAll(neighbors);
		
		// order by vertex value
		Collections.sort(neighborsList, comparator);
		
		for (Vertex neighbor : neighborsList) {
			if (!neighbor.getTeam().equals(WorldModel.myTeam)) {
				if (model.getAgentEntity().getRole().equals(Percept.ROLE_SABOTEUR)) {
					String vertex = Percept.VERTEX_PREFIX + neighbor.getId();
					return un.unifies(terms[0], ASSyntax.createString(vertex));
				} else if (!model.hasActiveOpponentOnVertex(neighbor)) {
					String vertex = Percept.VERTEX_PREFIX + neighbor.getId();
					return un.unifies(terms[0], ASSyntax.createString(vertex));
				}
			}
		}
		
//		if (!neighborsList.isEmpty()) {
//			Collections.shuffle(neighborsList);
//			Vertex v = neighborsList.get(0);
//			return un.unifies(terms[0], ASSyntax.createString(Percept.VERTEX_PREFIX + v.getId()));
//		} else {
//			return un.unifies(terms[0], ASSyntax.createString("none"));
//		}
		
		return un.unifies(terms[0], ASSyntax.createString("none"));
	}

}
