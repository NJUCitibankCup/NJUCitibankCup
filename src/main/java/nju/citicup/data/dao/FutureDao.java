package nju.citicup.data.dao;

import nju.citicup.common.entity.BasicFutureInfo;
import nju.citicup.common.entity.BasicOptionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lenovo on 2016/9/9.
 */
public interface FutureDao extends CrudRepository<BasicFutureInfo, String> {

    List<BasicFutureInfo> findByTarget(String target);

    @Modifying
    @Transactional
    @Query("update BasicFutureInfo b set b.sigma= :sigma where b.target= :target")
    int setSigma(@Param("target") String target, @Param("sigma") double sigma);


    @Modifying
    @Transactional
    @Query("update BasicFutureInfo b set b.quantity= :quantity where b.target= :target")
    int setQuantity(@Param("quantity") int quantity, @Param("target") String target);
}
