package nju.citicup.data.dao.baiscdao;

import nju.citicup.common.entity.BasicOptionInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by lenovo on 2016/9/9.
 */
public interface OptionDao extends CrudRepository<BasicOptionInfo, Integer> {
}