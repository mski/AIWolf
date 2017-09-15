package llc.littlewing.myagent;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.aiwolf.client.lib.Content;
import org.aiwolf.common.data.Agent;
import org.aiwolf.common.data.Judge;
import org.aiwolf.common.data.Player;
import org.aiwolf.common.data.Role;
import org.aiwolf.common.data.Status;
import org.aiwolf.common.data.Talk;
import org.aiwolf.common.net.GameInfo;
import org.aiwolf.common.net.GameSetting;

/**
 * Base Class
 */
public class MyBasePlayer implements Player {

	/** このAgent */
	Agent me;

	/** 日付 */
	int day;

	/** talk()できる時間帯か否か */
	boolean canTalk;

	/** whisper()出来る時間帯か否か */
	boolean canWhisper;

	/** 最新のゲーム情報 */
	GameInfo currentGameInfo;

	/** 自分以外の生存エージェント */
	List<Agent> aliveOthers;

	/** 追放されたエージェント */
	List<Agent> executedAgents = new ArrayList<>();

	/** 殺されたエージェント */
	List<Agent> killedAgents = new ArrayList<>();

	/** 発言された占い結果報告リスト */
	List<Judge> divinationList = new ArrayList<>();

	/** 発言された霊媒結果報告のリスト */
	List<Judge> identList = new ArrayList<>();

	/** 発言用待ち行列 */
	Deque<Content> talkQueue = new LinkedList<>();

	/** 囁き待ち行列 */
	Deque<Content> whisperQueue = new LinkedList<>();

	/** 投票先候補 */
	Agent voteCandidate;

	/** 宣言済み投票先候補 */
	Agent declaredVoteCandidate;

	/** 襲撃投票先候補 */
	Agent attackVoteCandidate;

	/** 宣言済み投票先候補 */
	Agent declaredAttackVoteCandidate;

	/** カミングアウト状況 */
	Map<Agent, Role> comingoutMap = new HashMap<>();

	/** GameInfo.talkList 読み込みのヘッド */
	int talkListHead;

	/** 人間リスト */
	List<Agent> humans = new ArrayList<>();

	/** 人狼リスト */
	List<Agent> werewolves = new ArrayList<>();

	/**
	 * エージェントが生きているかどうかを返す
	 *
	 * @param agent
	 * @return
	 */
	protected boolean isAlive(Agent agent) {
		return currentGameInfo.getStatusMap().get(agent) == Status.ALIVE;
	}

	/**
	 * エージェントが殺されているかどうかを返す
	 *
	 * @param agent
	 * @return
	 */
	protected boolean isKilled(Agent agent) {
		return comingoutMap.containsKey(agent);
	}

	/**
	 * エージェントがカミングアウトしたかどうかを返す
	 *
	 * @param agent
	 * @return
	 */
	protected boolean isCo(Agent agent) {
		return comingoutMap.containsKey(agent);
	}

	/**
	 * 役職がカミングアウトされたかどうかを返す
	 *
	 * @param role
	 * @return
	 */
	protected boolean isCo(Role role) {
		return comingoutMap.containsValue(role);
	}

	/**
	 * エージェントが人間かどうかを返す
	 *
	 * @param agent
	 * @return
	 */
	protected boolean isHuman(Agent agent) {
		return humans.contains(agent);
	}

	/**
	 * エージェントが人狼かどうかを返す
	 *
	 * @param agent
	 * @return
	 */
	protected boolean isWerewolf(Agent agent) {
		return werewolves.contains(agent);
	}

	/**
	 * リストからランダムに選んで返す
	 *
	 * @param list
	 * @return
	 */
	protected <T> T randomSelect(List<T> list) {
		Random rand = new Random();
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(rand.nextInt(list.size()));
		}
	}

	@Override
	public String getName() {
		return "MyBasePlayer";
	}

	@Override
	public void update(GameInfo gameInfo) {
		currentGameInfo = gameInfo;

		// 1日の最初の呼び出しはdayStart()の前なので何もしない
		if (currentGameInfo.getDay() == day + 1) {
			day = currentGameInfo.getDay();
			return;
		}

		// 2回目の呼び出し以降
		// (夜限定）追放されたエージェントを登録
		addExecutedAgent(currentGameInfo.getLatestExecutedAgent());
		// GameInfo.talklistからカミングアウト・占い報告・霊媒報告を抽出
		for (int i = talkListHead; i < currentGameInfo.getTalkList().size(); i++) {
			Talk talk = currentGameInfo.getTalkList().get(i);
			Agent talker = talk.getAgent();
			if (talker == me) {
				continue;
			}
			Content content = new Content(talk.getText());
			switch (content.getTopic()) {
			case COMINGOUT:
				comingoutMap.put(talker, content.getRole());
				break;
			case DIVINED:
				divinationList.add(new Judge(day, talker, content.getTarget(), content.getResult()));
				break;
			case IDENTIFIED:
				identList.add(new Judge(day, talker, content.getTarget(), content.getResult()));
				break;
			default:
				break;
			}
		}
		talkListHead = currentGameInfo.getTalkList().size();
	}

	/**
	 * 追放されたエージェントを登録
	 *
	 * @param executedAgent
	 */
	private void addExecutedAgent(Agent executedAgent) {
		if (executedAgent != null) {
			aliveOthers.remove(executedAgent);
			if (!executedAgents.contains(executedAgent)) {
				executedAgents.add(executedAgent);
			}
		}
	}

	@Override
	public void initialize(GameInfo gameInfo, GameSetting gameSetting) {
		day = -1;
		me = gameInfo.getAgent();
		aliveOthers = new ArrayList<>(gameInfo.getAliveAgentList());
		aliveOthers.remove(me);
		executedAgents.clear();
		divinationList.clear();
		identList.clear();
		comingoutMap.clear();
		humans.clear();
		werewolves.clear();
	}

	@Override
	public void dayStart() {

	}

	@Override
	public String talk() {
		return null;
	}

	@Override
	public String whisper() {
		return null;
	}

	@Override
	public Agent vote() {
		return null;
	}

	@Override
	public Agent attack() {
		return null;
	}

	@Override
	public Agent divine() {
		return null;
	}

	@Override
	public Agent guard() {
		return null;
	}

	@Override
	public void finish() {

	}
}
