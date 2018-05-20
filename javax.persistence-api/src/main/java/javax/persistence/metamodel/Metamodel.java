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
package javax.persistence.metamodel;

import java.util.Set;

/**
 * 永続化ユニット内の永続化エンティティのメタモデルへのアクセスを提供します。
 *
 * @since Java Persistence 2.0
 */
public interface Metamodel {

    /**
     *  Return the metamodel entity type representing the entity.
     *  @param cls  the type of the represented entity
     *  @return the metamodel entity type
     *  @throws IllegalArgumentException if not an entity
     */
    <X> EntityType<X> entity(Class<X> cls);

    /**
     *  Return the metamodel managed type representing the 
     *  entity, mapped superclass, or embeddable class.
     *  @param cls 表されたマネージドクラスの型
     *  @return メタモデルのマネージド型
     *  @throws IllegalArgumentException マネージドクラスでない場合
     */
    <X> ManagedType<X> managedType(Class<X> cls);
    
    /**
     *  組み込み可能型を表すメタモデルの組み込み型を返します。
     *  @param cls  表された組み込みクラスの型
     *  @return the メタモデルの組み込み型
     *  @throws IllegalArgumentException 組み込みクラスでない場合
     */
    <X> EmbeddableType<X> embeddable(Class<X> cls);

    /**
     *  メタモデルのマネージド型を返します。
     *  @return メタモデルのマネージド型
     */
    Set<ManagedType<?>> getManagedTypes();

    /**
     * メタモデルのエンティティ型を返します。
     * @return メタモデルのエンティティ型
     */
    Set<EntityType<?>> getEntities();

    /**
     * メタモデルの組み込み型を返します。 組み込み型がない場合は空のセットを返します。
     * @return メタモデルの組み込み型
     */
    Set<EmbeddableType<?>> getEmbeddables();
}
