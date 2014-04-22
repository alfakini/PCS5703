// Agent Repairer

/* Initial beliefs and rules */
is_not_help_goal				:-	not_need_help(Ag).
is_repair_goal					:-	need_help(Ag) & jia.agent_position(Ag,Pos) & position(Pos) & not jia.has_another_repairer.
is_wait_to_repair_goal			:- 	need_help(Ag) & jia.agent_position(Ag,Pos) & jia.is_neighbor_vertex(Pos) & not jia.has_another_repairer & not jia.has_repairer_at(Pos).

/* Initial goals */

+!repair_goal
	<-	//.print("Starting repair_goal"); 
		!select_repair_goal.

+!select_repair_goal
	:	is_call_help_goal
		<-	!init_goal(call_help);
				!!select_repair_goal.

+!select_repair_goal
	:	is_not_need_help_goal
	<-	!init_goal(not_need_help);
			!!select_repair_goal.

+!select_repair_goal
	:	is_energy_goal
	<-	!init_goal(be_at_full_charge);
			!!select_repair_goal.

+!select_repair_goal
	:	is_not_help_goal
	<-	!init_goal(not_help);
			!!select_repair_goal.

+!select_repair_goal
	:	is_parry_goal
	<-	!init_goal(parry);
			!!select_repair_goal.

+!select_repair_goal
	:	is_escape_goal
	<-	!init_goal(escape);
			!!select_repair_goal.

+!select_repair_goal
	:	is_repair_goal
	<-	!init_goal(repair);
			!!select_repair_goal.

+!select_repair_goal
	:	is_disabled_goal
	<-	!init_goal(go_to_repairer);
			!!select_repair_goal.

+!select_repair_goal
	:	need_help(Ag) & visRange(R) & R > 1 & jia.agent_position(Ag,Pos) & jia.is_neighbor_vertex(Pos)
	<-	!init_goal(repair_neighbor(Ag));
			!!select_repair_goal.

+!select_repair_goal
	: is_goto_fail_goal
	<-	!init_goal(goto_failled);
			!!select_repair_goal.

//+!select_repair_goal
//	:	is_move_goal
//	<-	!init_goal(move_to_target);
//			!!select_repair_goal.

+!select_repair_goal
	:	is_repairer_buy_goal
	<-	!init_goal(repairer_buy);
			!!select_repair_goal.

//+!select_repair_goal
//	: is_move_to_zone_goal
//	<-	!init_goal(move_to_zone);
//			!!select_repair_goal.

//+!select_repair_goal
//	: is_go_to_frontier_goal
//	<-	!init_goal(move_to_frontier);
//			!!select_repair_goal.

//+!select_repair_goal
//	: is_can_expand_goal
//	<-	!init_goal(expand);
//			!!select_repair_goal.

+!select_repair_goal
	: jia.is_move_to_saboteur_goal(NextPos)
	<-	!init_goal(move_to_saboteur(NextPos));
			!!select_repair_goal.

+!select_repair_goal
	: is_survey_goal
	<- 	!init_goal(survey);
			!!select_repair_goal.

+!select_repair_goal
	:	is_recharge_goal
	<-	!init_goal(be_at_full_charge);
			!!select_repair_goal.

//+!select_repair_goal
//	:	is_on_target_goal
//	<-	!init_goal(wait);
//			!!select_repair_goal.

+!select_repair_goal
	: is_move_to_zone_goal
	<-	!init_goal(move_to_zone);
			!!select_repair_goal.

+!select_repair_goal
	<- 	!init_goal(random_walk);
			!!select_repair_goal.




/* Repair plans */

+!repair
	: need_help(Ag) & jia.agent_position(Ag,Pos) & position(Pos)
	<-	jia.agent_server_id(Ag,Id);
		.send(Ag,tell,you_need_help);
		!do_and_wait_next_step(repair(Id)).

+!repair
	<-	!init_goal(wait).

+!repair_neighbor(Ag)
	: need_help(Ag)
	<-	jia.agent_server_id(Ag,Id);
		.send(Ag,tell,you_need_help);
		!do_and_wait_next_step(repair(Id)).

+!repair_neighbor(Ag)
	<-	!init_goal(wait).

+!not_help
	: need_help(Ag) & not_need_help(Ag)
	<-	.abolish(need_help(Ag));
			.abolish(not_need_help(Ag)).

+!not_help
	: not_need_help(Ag)
	<-	.abolish(not_need_help(Ag)).

+!move_to_saboteur(NextPos)
	<- !go_to(NextPos).


/* Buy plans */
//+!repairer_buy
//	: maxEnergy(E) & E >= 35 & maxHealth(X) & X >= 5
//	<- +stop_buy.

+!repairer_buy
	: buy_sensor
	<-	!buy(sensor).
//			-buy_sensor;
//			+buy_battery.

+!repairer_buy
	: buy_battery & maxEnergy(E) & E < 35
	<-	!buy(battery);
			-buy_battery;
			+buy_shield.

+!repairer_buy
	: buy_battery
	<-	-buy_battery;
			+buy_shield.

+!repairer_buy
	: buy_shield & maxHealth(X) & X < 5
	<-	!buy(shield);
			-buy_shield;
			+buy_battery.

+!repairer_buy
	: buy_shield
	<-	-buy_shield;
			+buy_battery.