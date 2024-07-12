package com.example.quizzzin.utilities;

import com.example.quizzzin.models.entities.AbstractPuzzle;
import com.example.quizzzin.models.entities.UserPuzzleRating;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

public class AbstractPuzzleSpecifications {
    public static Specification<AbstractPuzzle> byAverageRating() {
        return (root, query, cb) -> {
            Join<AbstractPuzzle, UserPuzzleRating> ratingJoin = root.join("puzzleRatings", JoinType.LEFT);

            // Calculate average rating
            Expression<Double> avgRating = cb.avg(ratingJoin.get("rating").as(Double.class));

            // Coalesce to handle cases where there might be no ratings yet
            Expression<Double> avgRatingCoalesce = cb.coalesce(avgRating, 0.0);

            // Select AbstractPuzzle entity and average rating
            query.multiselect(root, avgRatingCoalesce);

            // Group by AbstractPuzzle attributes to avoid duplicate rows
            query.groupBy(root.get("id"), root.get("title"), root.get("description"), root.get("answer"), root.get("difficulty"));

            return cb.isNotNull(avgRating); // Ensuring the average rating is not null
        };
    }
}
