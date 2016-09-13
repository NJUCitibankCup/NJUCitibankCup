package nju.citicup.bl;

import nju.citicup.common.vo.FutureIdMapEntry;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by admin on 2016/9/13.
 */
public interface FutureTypeService {

    public List<String> getFutureType();

    public List<FutureIdMapEntry> getEntryByType(String type);

}
