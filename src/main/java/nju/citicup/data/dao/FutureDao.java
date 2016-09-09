package nju.citicup.data.dao;

import nju.citicup.common.entity.BasicFutureInfo;
import nju.citicup.common.entity.BasicOptionInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by lenovo on 2016/9/9.
 */
public interface FutureDao extends CrudRepository<BasicFutureInfo, String> {

    List<BasicFutureInfo> findByTarget(String target);
}
