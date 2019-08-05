package review;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    ReviewRepository reviewRepository;

    // 리뷰 작성
    @RequestMapping(value = "/reviews", method = RequestMethod.POST)
    public ResponseEntity<Review> createReview(@RequestBody final Review review) {
        reviewRepository.save(review);
        return new ResponseEntity <> (review, HttpStatus.OK);
    }

    // 모든 리뷰 가져오기
    @RequestMapping(value = "/reviews", method = RequestMethod.GET)
    public ResponseEntity<List<Review>> getAllReview() {
        List <Review> reviewList = (List<Review>) reviewRepository.findAll();
        return new ResponseEntity<>(reviewList, HttpStatus.OK);
    }

    // 모든 리뷰 지우기 (테스트 용도로만 사용)
    @RequestMapping(value = "/reviews", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteAllReviews() {

        reviewRepository.deleteAll();
        return new ResponseEntity <> (HttpStatus.OK);
    }

    // 리뷰 하나 지우기
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.DELETE)
    public ResponseEntity<Review> deleteReview(@PathVariable(value="reviewId") long reviewId) {

        try {
            reviewRepository.deleteById(reviewId);
            return new ResponseEntity <> (HttpStatus.OK);
        }
        catch (Exception e) {
            log.error(e.toString());
            return new ResponseEntity <> (HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 리뷰 하나 가져오기
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.GET)
    public ResponseEntity<Review> getReview(@PathVariable(value="reviewId") long reviewId) {

        try {
            Review review = reviewRepository.findById(reviewId).get();
            return new ResponseEntity <> (review, HttpStatus.OK);
        }
        catch (Exception e) {
            log.error(e.toString());
            return new ResponseEntity <> (HttpStatus.NOT_FOUND);
        }
    }
}
