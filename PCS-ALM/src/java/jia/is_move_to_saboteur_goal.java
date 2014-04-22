package jia;

import jason.asSemantics.DefaultInternalAction;
import jason.asSemantics.TransitionSystem;
import jason.asSemantics.Unifier;
import jason.asSyntax.ASSyntax;
import jason.asSyntax.Term;

import java.util.Set;

import env.Percept;

import model.Entity;
import model.graph.Graph;
import model.graph.Vertex;
import arch.MartianArch;
import arch.WorldModel;

/**
 * Returns true or false indicating if the agents is probably part of the teams zone.
 * </p>
 * Use: jia.is_on_team_zone(Z);</br>
 * 
 * @author mafranko
 */
public class is_move_to_saboteur_goal extends DefaultInternalAction {

	private static final long serialVersionUID = 1168992507523306792L;
	
	@Override
	public Object execute(TransitionSystem ts, Unifier un, Term[] terms) throws Exception {
		WorldModel model = ((MartianArch) ts.getUserAgArch()).getModel();
		Vertex myPosition = model.getMyVertex();
		
		Vertex saboteurPosition = model.getSaboteurPosition();
		if (null == saboteurPosition) {
			return false;
		}
		
		Graph graph = model.getGraph();
		int dist = graph.getDistance(myPosition, saboteurPosition);
		if (dist > 2 && dist < Graph.MAX_DIST) {
			int nextMove = graph.returnNextMove(myPosition.getId(), saboteurPosition.getId());
			if (nextMove != -1) {
				String vertex = Percept.VERTEX_PREFIX + nextMove;
				return un.unifies(terms[0], ASSyntax.createString(vertex));
			}
		} else if (dist <= 2) {
			return un.unifies(terms[0], ASSyntax.createString("none"));
		} else {
			return false;
		}
		return false;
	}
}
