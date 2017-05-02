namespace java com.gitfresh.thrift.gfresh

struct GFreshUser {
	1: i32 id,
	2: string name,
	3: i32 followersCount,
	4: string location
}
struct SearchQueryResponse {
	1: list<GFreshUser> users
}
service GFresh {
	SearchQueryResponse searchQuery(1: string query);
}