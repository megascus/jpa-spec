/*******************************************************************************
 * Copyright (c) 2008 - 2013 Oracle Corporation. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Linda DeMichiel - Java Persistence 2.1
 *     Linda DeMichiel - Java Persistence 2.0
 *
 ******************************************************************************/ 
package javax.persistence;

import java.util.List;

/**
 * クエリーの結果タプルの要素を抽出するためのインタフェース。
 *
 * @see TupleElement
 *
 * @since Java Persistence 2.0
 */
public interface Tuple {

    /**
     * 指定されたタプル要素の値を取得します。
     * @param tupleElement  タプル要素
     * @return タプル要素の値
     * @throws IllegalArgumentException タプル要素がこのクエリーの結果タプル内の要素に対応しない場合
     */
    <X> X get(TupleElement<X> tupleElement);

    /**
     * 指定されたエイリアスで割り当てられているタプル要素の値を取得します。
     * @param alias  タプル要素に割り当てられているエイリアス
     * @param type タプル要素の型
     * @return  タプル要素の値
     * @throws IllegalArgumentException エイリアスがクエリーの結果タプル内の要素に対応しない場合、または指定された型に要素を割り当てることができない場合
     */
    <X> X get(String alias, Class<X> type); 

    /**
     * 指定されたエイリアスで割り当てられているタプル要素の値を取得します。
     * @param alias  タプル要素に割り当てられているエイリアス
     * @return タプル要素の値
     * @throws IllegalArgumentException エイリアスがクエリーの結果タプル内の要素に対応しない場合
     */
    Object get(String alias); 

    /**
     * 結果タプルの指定された位置の要素の値を取得します。
     * 
     * 最初の位置は0です。
     * @param i  結果タプルの位置
     * @param type  タプル要素の型
     * @return タプル要素の値
     * @throws IllegalArgumentException iが結果タプルの長さを超えた場合、または指定された型に要素を代入できない場合
     */
    <X> X get(int i, Class<X> type);

    /**
     * 結果タプルの指定された位置の要素の値を取得します。
     * 
     * 最初の位置は0です。
     * @param i  結果タプルの位置
     * @return タプル要素の値
     * @throws IllegalArgumentException iが結果タプルの長さを超えた場合
     */
    Object get(int i);

    /**
     * 結果タプル要素の値を配列として返します。
     * @return タプル要素の値
     */
    Object[] toArray();

    /**
     * タプル要素を返します。
     * @return タプル要素
     */
    List<TupleElement<?>> getElements();
}
