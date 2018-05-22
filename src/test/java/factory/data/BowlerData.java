package factory.data;

import com.mink.demo.bowlingapi.model.entities.Bowler;
import com.mink.demo.bowlingapi.service.dto.BowlerRequest;
import com.mink.demo.bowlingapi.service.dto.BowlerResponse;
import org.springframework.test.util.ReflectionTestUtils;

import static factory.util.RandomUtil.randomLong;
import static factory.util.RandomUtil.randomString;

public class BowlerData {

    public static Bowler getRandomBowler() {
        Bowler bowler = new Bowler();
        bowler.setName(randomString());
        ReflectionTestUtils.setField(bowler, "id", randomLong());
        return bowler;
    }

    public static BowlerRequest getRandomBowlerRequest() {
        return getRandomBowlerResponse();
    }

    public static BowlerResponse getRandomBowlerResponse() {
        BowlerResponse bowlerResponse = new BowlerResponse();
        bowlerResponse.setId(randomLong());
        bowlerResponse.setName(randomString());
        return bowlerResponse;
    }
    
}
