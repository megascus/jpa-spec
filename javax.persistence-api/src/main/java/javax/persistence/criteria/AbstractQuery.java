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
package javax.persistence.criteria;

import java.util.List;
import java.util.Set;
import javax.persistence.metamodel.EntityType;

/**
 * <code>AbstractQuery</code>インターフェースはトップレベルのクエリーとサブクエリーの両方に共通な機能を定義します。
 * 
 * これはクエリーの構築に直接使用するためのものではありません。
 * 
 * <p> すべてのクエリーは(joinを所有している可能性のある)ルートエンティティを持つ必要があります。
 * <p> すべてのクエリーは制限の論理積を持つ可能性があります。
 *
 * @param <T>  the type of the result
 *
 * @since Java Persistence 2.0
 */
public interface AbstractQuery<T> extends CommonAbstractCriteria {

    /**
     * 指定されたエンティティに対応するクエリールートを作成して追加し、既存のルートとのデカルト積を作成します。
     * @param entityClass  エンティティクラス
     * @return 指定されたエンティティに対応するクエリールート
     */
    <X> Root<X> from(Class<X> entityClass);

    /**
     * 指定されたエンティティに対応するクエリールートを作成して追加し、既存のルートとのデカルト積を作成します。
     * @param entity  タイプ X のエンティティに対応するメタモデルエンティティ
     * @return 指定されたエンティティに対応するクエリールート
     */
    <X> Root<X> from(EntityType<X> entity);

    /**
     * 指定されたブール式に従ってクエリ結果を制限するようにクエリを変更します。
     * 
     * 以前に追加された制限があれば置き換えます。
     * @param restriction  単純な、もしくは複合したブール式
     * @return 変更されたクエリー
     */    
    AbstractQuery<T> where(Expression<Boolean> restriction);

    /**
     * 指定されたブール式に従ってクエリ結果を制限するようにクエリを変更します。
     * 
     * 以前に追加された制限があれば置き換えます。
     * 制限が指定されていない場合は以前に追加された制限は単純に削除されます。
     * @param restrictions  0個以上の制限の述語
     * @return 変更されたクエリー
     */
    AbstractQuery<T> where(Predicate... restrictions);

    /**
     * クエリーの結果に対してグループを形成するために使用される式を指定します。
     * 
     * 以前に追加されたグループ化式があれば置き換えます。
     * グループ化式が指定されていない場合は以前に追加されたグループ化式は単純に削除されます。
     * @param grouping  0個以上のグループ化式
     * @return 変更されたクエリー
     */
    AbstractQuery<T> groupBy(Expression<?>... grouping);

    /**
     * クエリーの結果に対してグループを形成するために使用される式を指定します。
     * 
     * 以前に追加されたグループ化式があれば置き換えます。
     * グループ化式が指定されていない場合は以前に追加されたグループ化式は単純に削除されます。
     * @param grouping  0個以上のグループ化式の一覧
     * @return 変更されたクエリー
     */
    AbstractQuery<T> groupBy(List<Expression<?>> grouping);

    /**
     * クエリーのグループに対応する制限を指定します。
     * 
     * 以前に追加されたhaving制限があれば置き換えます。
     * @param restriction  単純な、もしくは複合したブール式
     * @return 変更されたクエリー
     */
    AbstractQuery<T> having(Expression<Boolean> restriction);

    /**
     * 指定された制限述語の結合に従ってクエリーのグループに対応する制限を指定します。
     *
     * 以前に追加されたhaving制限があれば置き換えます。
     * 制限が指定されていない場合は以前に追加された制限は単純に削除されます。
     * @param restrictions  0個以上の制限の述語
     * @return 変更されたクエリー
     */
    AbstractQuery<T> having(Predicate... restrictions);

    /**
     * 重複するクエリーの結果を除去するかどうかを指定します。
     * 
     * trueの値は重複を排除します。falseの値を指定すると重複が保持されます。
     * distinctが指定されていない場合は重複した結果を保持する必要があります。
     * @param distinct  クエリーの結果から重複結果を除去する必要があるかどうか、もしくは保持する必要があるかどうかを指定するブール値
     * @return 変更されたクエリー
     */
    AbstractQuery<T> distinct(boolean distinct);

    /**
     * クエリールートを返します。
     * 
     * 相関の結果として定義されたサブクエリールートを含む<code>CriteriaQuery</code>や<code>Subquery</code>自身で定義されたルートです。
     * いかなるルートも定義されていない場合は空のSetが返されます。
     * そのSetへの変更はクエリーには影響を与えません。
     * @return クエリールートのSet
     */   
    Set<Root<?>> getRoots();

    /**
     * クエリーの{@link Selection}を返します。{@link Selection}が設定されていない場合はnullを返します。
     *  @return 選択項目
     */
    Selection<T> getSelection();

    /**
     * グループ化式のListを返します。
     * 
     * グループ化式が指定されていない場合は空のListを返します。
     * そのListへの変更はクエリーには影響を与えません。
     * @return グループ化式のList
     */
    List<Expression<?>> getGroupList();

    /**
     * グループ化項目に対応する制限に対応する述語を返します。制限が指定されていない場合はnullを返します。
     * @return having句述語
     */
    Predicate getGroupRestriction();

    /**
     * 重複するクエリーの結果を除去または保持する必要があるかどうかを戻します。
     * @return 重複するクエリーの結果を削除する必要があるかどうかを示すブール値
     */
    boolean isDistinct();

    /**
     * クエリーもしくはサブクエリーの結果の型を返します。
     * 結果の型が<code>createQuery</code>もしくは<code>subquery</code>メソッドの引数として指定されている場合はその型が戻されます。
     * クエリーが<code>createTupleQuery</code>メソッドで作られている場合は結果の型は<code>Tuple</code>になります。
     * そうでない場合は<code>Object</code>です。
     * @return 結果の型
     */
    Class<T> getResultType();  	
}
