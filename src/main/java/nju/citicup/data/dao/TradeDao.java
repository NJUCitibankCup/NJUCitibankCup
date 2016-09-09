package nju.citicup.data.dao;

import nju.citicup.common.entity.BasicTradeInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by lenovo on 2016/9/9.
 */
public interface TradeDao extends CrudRepository<BasicTradeInfo, Integer> {
}
