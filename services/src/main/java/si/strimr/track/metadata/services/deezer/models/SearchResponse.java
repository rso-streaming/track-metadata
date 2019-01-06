package si.strimr.track.metadata.services.deezer.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchResponse {

    private List<SearchResponseData> data = new ArrayList<SearchResponseData>();
    private Integer total;

    public List<SearchResponseData> getData() {
        return data;
    }

    public void setData(List<SearchResponseData> data) {
        this.data = data;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
