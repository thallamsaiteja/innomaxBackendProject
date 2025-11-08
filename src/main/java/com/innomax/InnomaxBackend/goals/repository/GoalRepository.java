package com.innomax.InnomaxBackend.goals.repository;

import com.innomax.InnomaxBackend.goals.entity.Goal;
import com.innomax.InnomaxBackend.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {

    // Fetch all goals by user
    List<Goal> findByUser(User user);

    // Optional: find specific goal by user and goalId
    Goal findByIdAndUser(Long id, User user);

    // Count unique users who have goals
    @Query("select count(distinct g.user.userId) from Goal g")
    long countUsersWithGoals();

    // ✅ Top goal categories for bar chart
    @Query("SELECT g.category, COUNT(g) as cnt FROM Goal g GROUP BY g.category ORDER BY cnt DESC")
    List<Object[]> findTopGoals();

    // ✅ Goal distribution (for pie chart)
    @Query("SELECT g.category, COUNT(g) FROM Goal g GROUP BY g.category")
    List<Object[]> countGoalsByCategory();
}
