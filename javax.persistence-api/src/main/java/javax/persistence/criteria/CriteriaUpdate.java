/*******************************************************************************
 * Copyright (c) 2011 - 2013 Oracle Corporation. All rights reserved.
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
 *
 ******************************************************************************/ 
package javax.persistence.criteria;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.EntityType;

/**
 * <code>CriteriaUpdate</code>インターフェイスはCriteria APIを使用して一括更新操作を実行するための機能を定義します。
 *
 * <p>Criteria APIの一括更新操作はいかなる楽観ロックチェックも迂回し、データベースの更新操作に直接対応します。
 * 一括更新処理を使用する移植性のあるアプリケーションでは必要に応じてバージョン列の値を手動で更新したり、
 * バージョン列の値を手動で検証する必要があります。 永続化コンテキストは一括更新の結果と同期化されません。
 *
 * <p> <code>CriteriaUpdate</code>オブジェクトは必ず単一のルートのみを持ちます。
 *
 * @param <T>  更新される対象のエンティティ型
 *
 * @since Java Persistence 2.1
 */
public interface CriteriaUpdate<T> extends CommonAbstractCriteria {

   /**
    * 更新の対象になるエンティティに対応するクエリールートを作成して追加します。
    * 
    * <code>CriteriaUpdate</code>オブジェクトは削除されるエンティティを示す単一のルートのみを持ちます。
    * @param entityClass  エンティティクラス
    * @return 与えられたエンティティに対応するクエリールート
    */
   Root<T> from(Class<T> entityClass);

   /**
     * 更新の対象となるエンティティに対応するクエリールートを作成して追加します。
     * 
     * <code>CriteriaUpdate</code>オブジェクトは削除されるエンティティを示す単一のルートのみを持ちます。
     * @param entity  タイプ T のエンティティに対応するメタモデルエンティティ
     * @return 与えられたエンティティに対応するクエリールート
    */
   Root<T> from(EntityType<T> entity);

   /**
    * クエリールートを返します。
    * @return クエリールート
    */
   Root<T> getRoot();

   /**
    * 指定された属性の値を更新します。
    * @param attribute  更新される属性
    * @param value  新しい値
    * @return  変更された更新クエリー
    */
   <Y, X extends Y> CriteriaUpdate<T> set( SingularAttribute<? super T, Y> attribute, X value);

   /**
    * 指定された属性の値を更新します。
    * @param attribute  更新される属性
    * @param value  新しい値
    * @return  変更された更新クエリー
    */
   <Y> CriteriaUpdate<T> set( SingularAttribute<? super T, Y> attribute, Expression<? extends Y> value);

   /**
    * 指定された属性の値を更新します。
    * @param attribute  更新される属性
    * @param value  新しい値
    * @return  変更された更新クエリー
    */
   <Y, X extends Y> CriteriaUpdate<T> set(Path<Y> attribute, X value);

   /**
    * 指定された属性の値を更新します。
    * @param attribute  更新される属性
    * @param value  新しい値
    * @return  変更された更新クエリー
    */
   <Y> CriteriaUpdate<T> set(Path<Y> attribute, Expression<? extends Y> value);

   /**
    * 指定された属性の値を更新します。
    * @param attributeName  更新される属性の名前
    * @param value  新しい値
    * @return  変更された更新クエリー
    */
   CriteriaUpdate<T> set(String attributeName, Object value);

    /**
     * 更新クエリーを変更して、指定されたブール式に従って更新する対象を制限します。
     * 
     * 以前に追加された制限があれば置き換えます。
     * @param restriction  単純な、もしくは複合したブール式
     * @return 変更された更新クエリー
     */    
   CriteriaUpdate<T> where(Expression<Boolean> restriction);

    /**
     * 更新クエリーを変更して、指定されたブール式に従って更新する対象を制限します。
     * 
     * 以前に追加された制限があれば置き換えます。
     * 制限が指定されていない場合は以前に追加された制限は単純に削除されます。
     * @param restrictions  0個以上の制限の述語
     * @return 変更された更新クエリー
     */
   CriteriaUpdate<T> where(Predicate... restrictions);
}
