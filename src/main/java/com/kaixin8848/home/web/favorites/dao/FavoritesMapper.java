package com.kaixin8848.home.web.favorites.dao;

import com.kaixin8848.home.core.Mapper;
import com.kaixin8848.home.web.favorites.model.Favorites;
import com.kaixin8848.home.web.favorites.pojo.in.FavoritesCondition;

import java.util.List;


public interface FavoritesMapper extends Mapper<Favorites> {
    /**
     * 查询收藏夹
     *
     * @param favoritesCondition
     * @return
     */
    List<Favorites> findFavorites(FavoritesCondition favoritesCondition);
}