package com.neo.thymeleaf.entity;

import java.util.Date;

/**
 * Created by jason on 2017/11/30.
 */
public class User {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getFollowers_url() {
        return followers_url;
    }

    public void setFollowers_url(String followers_url) {
        this.followers_url = followers_url;
    }

    public String getFollowing_url() {
        return following_url;
    }

    public void setFollowing_url(String following_url) {
        this.following_url = following_url;
    }

    public String getGists_url() {
        return gists_url;
    }

    public void setGists_url(String gists_url) {
        this.gists_url = gists_url;
    }

    public String getStarred_url() {
        return starred_url;
    }

    public void setStarred_url(String starred_url) {
        this.starred_url = starred_url;
    }

    public String getSubscriptions_url() {
        return subscriptions_url;
    }

    public void setSubscriptions_url(String subscriptions_url) {
        this.subscriptions_url = subscriptions_url;
    }

    public String getOrganizations_url() {
        return organizations_url;
    }

    public void setOrganizations_url(String organizations_url) {
        this.organizations_url = organizations_url;
    }

    public String getRepos_url() {
        return repos_url;
    }

    public void setRepos_url(String repos_url) {
        this.repos_url = repos_url;
    }

    public String getEvents_url() {
        return events_url;
    }

    public void setEvents_url(String events_url) {
        this.events_url = events_url;
    }

    public String getReceived_events_url() {
        return received_events_url;
    }

    public void setReceived_events_url(String received_events_url) {
        this.received_events_url = received_events_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSite_admin() {
        return site_admin;
    }

    public void setSite_admin(boolean site_admin) {
        this.site_admin = site_admin;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getWeibo() {
        return weibo;
    }

    public void setWeibo(String weibo) {
        this.weibo = weibo;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getPublic_repos() {
        return public_repos;
    }

    public void setPublic_repos(int public_repos) {
        this.public_repos = public_repos;
    }

    public int getPublic_gists() {
        return public_gists;
    }

    public void setPublic_gists(int public_gists) {
        this.public_gists = public_gists;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public int getStared() {
        return stared;
    }

    public void setStared(int stared) {
        this.stared = stared;
    }

    public int getWatched() {
        return watched;
    }

    public void setWatched(int watched) {
        this.watched = watched;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUnconfirmed_email() {
        return unconfirmed_email;
    }

    public void setUnconfirmed_email(String unconfirmed_email) {
        this.unconfirmed_email = unconfirmed_email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPrivate_token() {
        return private_token;
    }

    public void setPrivate_token(String private_token) {
        this.private_token = private_token;
    }

    public String getTotal_repos() {
        return total_repos;
    }

    public void setTotal_repos(String total_repos) {
        this.total_repos = total_repos;
    }

    public String getOwned_repos() {
        return owned_repos;
    }

    public void setOwned_repos(String owned_repos) {
        this.owned_repos = owned_repos;
    }

    public String getTotal_private_repos() {
        return total_private_repos;
    }

    public void setTotal_private_repos(String total_private_repos) {
        this.total_private_repos = total_private_repos;
    }

    public String getOwned_private_repos() {
        return owned_private_repos;
    }

    public void setOwned_private_repos(String owned_private_repos) {
        this.owned_private_repos = owned_private_repos;
    }

    public String getPrivate_gists() {
        return private_gists;
    }

    public void setPrivate_gists(String private_gists) {
        this.private_gists = private_gists;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String login;

    private String name;

    private String avatar_url;

    private String url;

    private String html_url;

    private String followers_url;

    private String following_url;

    private String gists_url;

    private String starred_url;

    private String subscriptions_url;

    private String organizations_url;

    private String repos_url;

    private String events_url;

    private String received_events_url;

    private String type;

    private boolean site_admin;

    private String blog;

    private String weibo;

    private String bio;

    private int public_repos;

    private int public_gists;

    private int followers;

    private int following;

    private int stared;

    private int watched;

    private Date  created_at;

    private Date updated_at;

    private String email;

    private String unconfirmed_email;

    private String phone;

    private String private_token;

    private String total_repos;

    private String owned_repos;

    private String total_private_repos;

    private String owned_private_repos;

    private String private_gists;

    private String address;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", avatar_url='" + avatar_url + '\'' +
                ", url='" + url + '\'' +
                ", html_url='" + html_url + '\'' +
                ", followers_url='" + followers_url + '\'' +
                ", following_url='" + following_url + '\'' +
                ", gists_url='" + gists_url + '\'' +
                ", starred_url='" + starred_url + '\'' +
                ", subscriptions_url='" + subscriptions_url + '\'' +
                ", organizations_url='" + organizations_url + '\'' +
                ", repos_url='" + repos_url + '\'' +
                ", events_url='" + events_url + '\'' +
                ", received_events_url='" + received_events_url + '\'' +
                ", type='" + type + '\'' +
                ", site_admin=" + site_admin +
                ", blog='" + blog + '\'' +
                ", weibo='" + weibo + '\'' +
                ", bio='" + bio + '\'' +
                ", public_repos=" + public_repos +
                ", public_gists=" + public_gists +
                ", followers=" + followers +
                ", following=" + following +
                ", stared=" + stared +
                ", watched=" + watched +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", email='" + email + '\'' +
                ", unconfirmed_email='" + unconfirmed_email + '\'' +
                ", phone='" + phone + '\'' +
                ", private_token='" + private_token + '\'' +
                ", total_repos='" + total_repos + '\'' +
                ", owned_repos='" + owned_repos + '\'' +
                ", total_private_repos='" + total_private_repos + '\'' +
                ", owned_private_repos='" + owned_private_repos + '\'' +
                ", private_gists='" + private_gists + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
