package com.gitfresh.gfresh;


import com.gitfresh.thrift.gfresh.GFresh;

import com.gitfresh.thrift.gfresh.SearchQueryResponse;


import java.util.ArrayList;
import java.util.List;
/**
 * Created by zma on 5/2/17.
 */
public class SearchQueryImpl implements GFresh.Iface {
    public SearchQueryResponse searchQuery(String query) throws org.apache.thrift.TException {
        List<com.gitfresh.thrift.gfresh.GFreshUser> users = new ArrayList<com.gitfresh.thrift.gfresh.GFreshUser>();
        for (com.gitfresh.gfresh.GFreshUser user: UserService.getUserService().searchByQuery(query)) {
            users.add(new com.gitfresh.thrift.gfresh.GFreshUser(user.getId(), user.getUserName(), user.getFollowersCount(), user.getLocation()));
        }
        return new SearchQueryResponse(users);
    }
}
