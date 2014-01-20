package com.esgi.myratp.mapper;

public interface IStationMapper<TSource, TResult>
{
    TResult Map(TSource item);
}
