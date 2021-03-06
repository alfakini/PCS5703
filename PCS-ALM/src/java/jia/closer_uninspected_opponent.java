package jia;

import jason.asSemantics.DefaultInternalAction;
import jason.asSemantics.TransitionSystem;
import jason.asSemantics.Unifier;
import jason.asSyntax.ASSyntax;
import jason.asSyntax.Term;

import java.util.List;

import env.Percept;

import model.Entity;
import model.graph.Graph;
import model.graph.Vertex;
import arch.MartianArch;
import arch.WorldModel;

/**
 * Returns the position of the closer not inspected opponent.
 * </p>
 * Use: jia.closer_opponent(-P); </br>
 * Where: P is the position of the closer opponent uninspected opponent.
 * 
 * @author mafranko
 */
public class closer_uninspected_opponent extends DefaultInternalAction {

	private static final long serialVersionUID = 7802609769068834646L;

	@Override
	public Object execute(TransitionSystem ts, Unifier un, Term[] terms) throws Exception {
		WorldModel model = ((MartianArch) ts.getUserAgArch()).getModel();
		Graph graph = model.getGraph();

		Vertex myPosition = model.getMyVertex();

		List<Entity> opponents = model.getUninspectedOpponents();

		if (!opponents.isEmpty()) {
			int minDist = Integer.MAX_VALUE;
			int closerPosition = -1;
			for (Entity opponent : opponents) {
				Vertex v = opponent.getVertex();
				int dist = graph.getDistance(myPosition, v);
				if (dist < minDist) {
					closerPosition = v.getId();
					minDist = dist;
				}
			}
			if (closerPosition == -1) {	// random walk
				int nextMove = graph.returnLeastVisitedNeighbor(myPosition.getId());
				if (nextMove == -1) {
					return un.unifies(terms[0], ASSyntax.createString("none"));
				}
				String vertex = Percept.VERTEX_PREFIX + nextMove;
				return un.unifies(terms[0], ASSyntax.createString(vertex));
			}
			String vertex = Percept.VERTEX_PREFIX + closerPosition;
			return un.unifies(terms[0], ASSyntax.createString(vertex));
		} else {	// random walk
			int nextMove = graph.returnLeastVisitedNeighbor(myPosition.getId());
			if (nextMove == -1) {
				return un.unifies(terms[0], ASSyntax.createString("none"));
			}
			String vertex = Percept.VERTEX_PREFIX + nextMove;
			return un.unifies(terms[0], ASSyntax.createString(vertex));
		}
	}
}
