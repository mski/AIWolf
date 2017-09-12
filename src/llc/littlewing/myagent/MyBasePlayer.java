package llc.littlewing.myagent;

import org.aiwolf.common.data.Agent;
import org.aiwolf.common.data.Judge;
import org.aiwolf.common.data.Player;
import org.aiwolf.common.net.GameInfo;
import org.aiwolf.common.net.GameSetting;

import java.util.ArrayList;
import java.util.List;

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

    List<Agent> executedAgents = new ArrayList<>();

    List<Agent> killedAgents = new ArrayList<>();

    /** 発言された占い結果報告リスト */
    List<Judge> divinationList = new ArrayList<>();


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

