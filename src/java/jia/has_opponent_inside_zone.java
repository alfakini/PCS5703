package jia;

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
import jason.asSyntax.ListTerm;
import jason.asSyntax.NumberTerm;
import jason.asSyntax.Term;

/**
 * Returns true or false indicating if exists an opponent in the same vertex as me.
 * </p>
 * Use: jia.has_opponent_on_vertex;</br>
 * 
 * @author mafranko
 */
public class has_opponent_inside_zone extends DefaultInternalAction {

	private static final long serialVersionUID = 1182494310168253005L;

	@Override
	public Object execute(TransitionSystem ts, Unifier un, Term[] terms) throws Exception {
		WorldModel model = ((MartianArch) ts.getUserAgArch()).getModel();
		Graph graph = model.getGraph();
		Vertex myPosition = model.getMyVertex();
		
		Vertex nextMove = graph.getNextPosToOpponentOnZone(myPosition);
		
		if (null == nextMove) {
			return false;
		}
		
		String vertex = Percept.VERTEX_PREFIX + nextMove.getId();
		return un.unifies(terms[0], ASSyntax.createString(vertex));
	}
}
