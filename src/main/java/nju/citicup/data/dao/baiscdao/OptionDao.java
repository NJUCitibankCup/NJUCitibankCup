package nju.citicup.data.dao.baiscdao;

import nju.citicup.common.entity.BasicOptionInfo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lenovo on 2016/9/9.
 */
public interface OptionDao extends CrudRepository<BasicOptionInfo, Integer> {

    @Modifying
    @Transactional
    @Query("update BasicOptionInfo b set b.cost= :cost where b.id= :id")
    int updateCost(@Param("cost") double cost, @Param("id") int id);
}
