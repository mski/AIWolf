package llc.littlewing.myagent;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.aiwolf.client.lib.Content;
import org.aiwolf.common.data.Agent;
import org.aiwolf.common.data.Judge;
import org.aiwolf.common.data.Player;
import org.aiwolf.common.data.Role;
import org.aiwolf.common.data.Status;
import org.aiwolf.common.net.GameInfo;
import org.aiwolf.common.net.GameSetting;

/**
 * Base Class
 */
public class MyBasePlayer implements Player{

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

    /** 追放されたエージェント*/
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
    Map<Agent, Role> comingoutMap = new HashMap();

    /** GameInfo.talkList 読み込みのヘッド */
    int talkListHead;

    /** 人間リスト */
    List<Agent> humans = new ArrayList<>();

    /** 人狼リスト */
    List<Agent> werewolves = new ArrayList<>();

    /** エージェントが生きているかどうかを返す */
    protected boolean isAlive (Agent agent) {
        return currentGameInfo.getStatusMap().get(agent) == Status.ALIVE;
    }



    @Override
    public String getName() {
        return null;
    }

    @Override
    public void update(GameInfo gameInfo) {

    }

    @Override
    public void initialize(GameInfo gameInfo, GameSetting gameSetting) {

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

