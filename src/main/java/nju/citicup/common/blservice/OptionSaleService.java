package nju.citicup.common.blservice;

import nju.citicup.common.enumarate.OptionType;
import nju.citicup.common.vo.FutureIdMapEntry;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by admin on 2016/9/13.
 */
public interface OptionSaleService {

    public List<String> getFutureType();

    public List<FutureIdMapEntry> getEntryByType(String type);

    public double getOptionPrice
            (String futuresId,OptionType type,double executivePrice,Integer H);

    public void sellOption
            (String futuresId,OptionType type,double executivePrice,Integer H);
}
