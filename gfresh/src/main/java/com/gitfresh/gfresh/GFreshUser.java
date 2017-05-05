package com.gitfresh.gfresh;

import java.net.URL;
import java.util.Map;

import org.kohsuke.github.GHRepository;
import org.springframework.data.annotation.Id;

public class GFreshUser {
	@Id
	private int id;
	private String userName;
	private String name;
	private int followersCount;
	private String location;
	private String email;
	private String url;
	private String htmlUrl;
	private String blog;
	private String company;
	private int javaRepoCount;
	private int selfownedJavaRepoCount;
	private int javascriptRepoCount;
	private int selfownedJavascriptRepoCount;
	private int groovyRepoCount;
	private int selfownedGroovyRepoCount;
	private int scalaRepoCount;
	private int selfownedScalaRepoCount;
	private int goRepoCount;
	private int selfownedGoRepoCount;
	private int rubyRepoCount;
	private int selfownedRubyRepoCount;
	private int otherRepoCount;
	private int selfownedOtherRepoCount;
	private int publicRepoCount;
	private String bio;
	private String hireable;

	public GFreshUser() {

	}

	public GFreshUser(int id, String userName, String name, int followersCount, String location, String email, URL url,
			URL htmlUrl, String blog, String company, Map<String, GHRepository> repositories, int publicRepoCount) {
		this.id = id;
		this.userName = userName;
		this.name = name;
		this.followersCount = followersCount;
		this.location = location;
		this.email = email;
		this.url = url == null ? null : url.toString();
		this.htmlUrl = htmlUrl == null ? null : htmlUrl.toString();
		this.blog = blog;
		this.company = company;
		for (GHRepository repository : repositories.values()) {
			if ("Java".equalsIgnoreCase(repository.getLanguage())) {
				this.javaRepoCount++;
				if (!repository.isFork()) {
					selfownedJavaRepoCount++;
				}
			} else if ("JavaScript".equalsIgnoreCase(repository.getLanguage())) {
				this.javascriptRepoCount++;
				if (!repository.isFork()) {
					selfownedJavascriptRepoCount++;
				}
			} else if ("Groovy".equalsIgnoreCase(repository.getLanguage())) {
				this.groovyRepoCount++;
				if (!repository.isFork()) {
					selfownedGroovyRepoCount++;
				}
			} else if ("Scala".equalsIgnoreCase(repository.getLanguage())) {
				this.scalaRepoCount++;
				if (!repository.isFork()) {
					selfownedScalaRepoCount++;
				}
			} else if ("Go".equalsIgnoreCase(repository.getLanguage())) {
				this.goRepoCount++;
				if (!repository.isFork()) {
					selfownedGoRepoCount++;
				}
			} else if ("Ruby".equalsIgnoreCase(repository.getLanguage())) {
				this.rubyRepoCount++;
				if (!repository.isFork()) {
					selfownedRubyRepoCount++;
				}
			} else {
				this.otherRepoCount++;
				if (!repository.isFork()) {
					selfownedOtherRepoCount++;
				}
			}
		}
		this.publicRepoCount = publicRepoCount;
		this.bio = "";
		this.hireable = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFollowersCount() {
		return followersCount;
	}

	public void setFollowersCount(int followersCount) {
		this.followersCount = followersCount;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHtmlUrl() {
		return htmlUrl;
	}

	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}

	public String getBlog() {
		return blog;
	}

	public void setBlog(String blog) {
		this.blog = blog;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getJavaRepoCount() {
		return javaRepoCount;
	}

	public void setJavaRepoCount(int javaRepoCount) {
		this.javaRepoCount = javaRepoCount;
	}

	public int getJavascriptRepoCount() {
		return javascriptRepoCount;
	}

	public void setJavascriptRepoCount(int javascriptRepoCount) {
		this.javascriptRepoCount = javascriptRepoCount;
	}

	public int getGroovyRepoCount() {
		return groovyRepoCount;
	}

	public void setGroovyRepoCount(int groovyRepoCount) {
		this.groovyRepoCount = groovyRepoCount;
	}

	public int getScalaRepoCount() {
		return scalaRepoCount;
	}

	public void setScalaRepoCount(int scalaRepoCount) {
		this.scalaRepoCount = scalaRepoCount;
	}

	public int getGoRepoCount() {
		return goRepoCount;
	}

	public void setGoRepoCount(int goRepoCount) {
		this.goRepoCount = goRepoCount;
	}

	public int getRubyRepoCount() {
		return rubyRepoCount;
	}

	public void setRubyRepoCount(int rubyRepoCount) {
		this.rubyRepoCount = rubyRepoCount;
	}

	public int getOtherRepoCount() {
		return otherRepoCount;
	}

	public void setOtherRepoCount(int otherRepoCount) {
		this.otherRepoCount = otherRepoCount;
	}

	public int getPublicRepoCount() {
		return publicRepoCount;
	}

	public void setPublicRepoCount(int publicRepoCount) {
		this.publicRepoCount = publicRepoCount;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getHireable() {
		return hireable;
	}

	public void setHireable(String hireable) {
		this.hireable = hireable;
	}

	public int getSelfownedJavaRepoCount() {
		return selfownedJavaRepoCount;
	}

	public void setSelfownedJavaRepoCount(int selfownedJavaRepoCount) {
		this.selfownedJavaRepoCount = selfownedJavaRepoCount;
	}

	public int getSelfownedJavascriptRepoCount() {
		return selfownedJavascriptRepoCount;
	}

	public void setSelfownedJavascriptRepoCount(int selfownedJavascriptRepoCount) {
		this.selfownedJavascriptRepoCount = selfownedJavascriptRepoCount;
	}

	public int getSelfownedGroovyRepoCount() {
		return selfownedGroovyRepoCount;
	}

	public void setSelfownedGroovyRepoCount(int selfownedGroovyRepoCount) {
		this.selfownedGroovyRepoCount = selfownedGroovyRepoCount;
	}

	public int getSelfownedScalaRepoCount() {
		return selfownedScalaRepoCount;
	}

	public void setSelfownedScalaRepoCount(int selfownedScalaRepoCount) {
		this.selfownedScalaRepoCount = selfownedScalaRepoCount;
	}

	public int getSelfownedGoRepoCount() {
		return selfownedGoRepoCount;
	}

	public void setSelfownedGoRepoCount(int selfownedGoRepoCount) {
		this.selfownedGoRepoCount = selfownedGoRepoCount;
	}

	public int getSelfownedRubyRepoCount() {
		return selfownedRubyRepoCount;
	}

	public void setSelfownedRubyRepoCount(int selfownedRubyRepoCount) {
		this.selfownedRubyRepoCount = selfownedRubyRepoCount;
	}

	public int getSelfownedOtherRepoCount() {
		return selfownedOtherRepoCount;
	}

	public void setSelfownedOtherRepoCount(int selfownedOtherRepoCount) {
		this.selfownedOtherRepoCount = selfownedOtherRepoCount;
	}

	@Override
	public String toString() {
		return "User ID: " + id + " -User Name: " + userName + " -Name: " + name + " -Followers: " + followersCount
				+ " -Location: " + location + " -Email: " + email + " -HtmlURL: " + htmlUrl + " -Company: " + company
				+ " -Java Repo Count: " + javaRepoCount + " -Public Repo Count: " + publicRepoCount + " Bio: " + bio
				+ " Hireable: " + hireable;
	}
}
