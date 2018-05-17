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
 *  <code>ManagedType</code>型のインスタンスはエンティティやマップドスーパークラス、組み込み型を表します。
 *
 *  @param <X> 表される型
 *
 *  @since Java Persistence 2.0
 *
 */
public interface ManagedType<X> extends Type<X> {

    /**
     *  マネージド型の属性を返します。
     *  @return マネージド型の属性
     */
     Set<Attribute<? super X, ?>> getAttributes();

    /**
     *  マネージド型によって宣言された属性を返します。
     * 
     *  マネージド型に宣言された属性がない場合は空のセットを返します。
     *  @return マネージド型によって宣言された属性
     */
     Set<Attribute<X, ?>> getDeclaredAttributes();

    /**
     *  指定された名前とJava型に対応するマネージド型の単一値の属性を返します。
     *  @param name  表現された属性の名前
     *  @param type 表現された属性の型
     *  @return 指定された名前と型の単一値の属性
     *  @throws IllegalArgumentException 指定された名前と型の属性がマネージド型に存在しない場合
     */
    <Y> SingularAttribute<? super X, Y> getSingularAttribute(String name, Class<Y> type);

    /**
     *  指定された名前とJava型に対応するマネージド型によって宣言された単一値の属性を返します。
     *  @param name  表現された属性の名前
     *  @param type 表現された属性の型
     *  @return 指定された名前と型の宣言された単一値の属性
     *  @throws IllegalArgumentException 指定された名前と型の宣言された属性がマネージド型に存在しない場合
     */
    <Y> SingularAttribute<X, Y> getDeclaredSingularAttribute(String name, Class<Y> type);

    /**
     *  マネージド型の単一値の属性を返します。
     * 
     *  マネージド型に単一値の属性がない場合は空のセットを返します。
     *  @return 単一値の属性
     */
    Set<SingularAttribute<? super X, ?>> getSingularAttributes();

    /**
     *  マネージド型によって宣言された単一値の属性を返します。
     * 
     *  マネージド型に宣言された単一値の属性がない場合は空のセットを返します。
     *  @return 宣言された単一値の属性
     */
    Set<SingularAttribute<X, ?>> getDeclaredSingularAttributes();

    /**
     *  Return the Collection-valued attribute of the managed type 
     *  that corresponds to the specified name and Java element type.
     *  @param name  表現された属性の名前
     *  @param elementType  表現された属性の要素の型
     *  @return 与えられた名前と要素型のCollectionAttribute
     *  @throws IllegalArgumentException if attribute of the given
     *          name and type is not present in the managed type
     */    
    <E> CollectionAttribute<? super X, E> getCollection(String name, Class<E> elementType);

    /**
     *  Return the Collection-valued attribute declared by the 
     *  managed type that corresponds to the specified name and Java 
     *  element type.
     *  @param name  表現された属性の名前
     *  @param elementType  表現された属性の要素の型
     *  @return 与えられた名前と要素型の宣言された<code>CollectionAttribute</code>
     *  @throws IllegalArgumentException if attribute of the given
     *          name and type is not declared in the managed type
     */
    <E> CollectionAttribute<X, E> getDeclaredCollection(String name, Class<E> elementType);

    /**
     *  Return the Set-valued attribute of the managed type that
     *  corresponds to the specified name and Java element type.
     *  @param name  表現された属性の名前
     *  @param elementType  表現された属性の要素の型
     *  @return 与えられた名前と要素型のSetAttribute
     *  @throws IllegalArgumentException if attribute of the given
     *          name and type is not present in the managed type
     */
    <E> SetAttribute<? super X, E> getSet(String name, Class<E> elementType);

    /**
     *  Return the Set-valued attribute declared by the managed type 
     *  that corresponds to the specified name and Java element type.
     *  @param name  表現された属性の名前
     *  @param elementType  表現された属性の要素の型
     *  @return 与えられた名前と要素型の宣言されたSetAttribute
     *  @throws IllegalArgumentException if attribute of the given
     *          name and type is not declared in the managed type
     */
    <E> SetAttribute<X, E> getDeclaredSet(String name, Class<E> elementType);

    /**
     *  Return the List-valued attribute of the managed type that
     *  corresponds to the specified name and Java element type.
     *  @param name  表現された属性の名前
     *  @param elementType  表現された属性の要素の型
     *  @return 与えられた名前と要素型のListAttribute
     *  @throws IllegalArgumentException if attribute of the given
     *          name and type is not present in the managed type
     */
    <E> ListAttribute<? super X, E> getList(String name, Class<E> elementType);

    /**
     *  Return the List-valued attribute declared by the managed 
     *  type that corresponds to the specified name and Java 
     *  element type.
     *  @param name  表現された属性の名前
     *  @param elementType  表現された属性の要素の型
     *  @return 与えられた名前と要素型の宣言されたListAttribute
     *  @throws IllegalArgumentException if attribute of the given
     *          name and type is not declared in the managed type
     */
    <E> ListAttribute<X, E> getDeclaredList(String name, Class<E> elementType);

    /**
     *  Return the Map-valued attribute of the managed type that
     *  corresponds to the specified name and Java key and value
     *  types.
     *  @param name  表現された属性の名前
     *  @param keyType  表現された属性のキーの型
     *  @param valueType  表現された属性の値の型
     *  @return 与えられた名前と要素型のMapAttribute
     *  @throws IllegalArgumentException if attribute of the given
     *          name and type is not present in the managed type
     */
    <K, V> MapAttribute<? super X, K, V> getMap(String name, 
                                                Class<K> keyType, 
                                                Class<V> valueType);

    /**
     *  Return the Map-valued attribute declared by the managed 
     *  type that corresponds to the specified name and Java key 
     *  and value types.
     *  @param name  表現された属性の名前
     *  @param keyType  表現された属性のキーの型
     *  @param valueType  表現された属性の値の型
     *  @return 与えられた名前と要素型の宣言されたMapAttribute
     *  @throws IllegalArgumentException if attribute of the given
     *          name and type is not declared in the managed type
     */
    <K, V> MapAttribute<X, K, V> getDeclaredMap(String name, 
                                                Class<K> keyType, 
                                                Class<V> valueType);
    
    /**
     *  Return all multi-valued attributes (Collection-, Set-,
     *  List-, and Map-valued attributes) of the managed type.
     *  Returns empty set if the managed type has no multi-valued
     *  attributes.
     *  @return コレクション値、セット値、リスト値、マップ値のいずれかの属性
     */
    Set<PluralAttribute<? super X, ?, ?>> getPluralAttributes();

    /**
     *  Return all multi-valued attributes (Collection-, Set-,
     *  List-, and Map-valued attributes) declared by the 
     *  managed type.
     *  Returns empty set if the managed type has no declared
     *  multi-valued attributes.
     *  @return 宣言されたコレクション値、セット値、リスト値、マップ値のいずれかの属性
     */
    Set<PluralAttribute<X, ?, ?>> getDeclaredPluralAttributes();


//String-based:

    /**
     *  Return the attribute of the managed
     *  type that corresponds to the specified name.
     *  @param name  表現された属性の名前
     *  @return 与えられた名前の属性
     *  @throws IllegalArgumentException 与えられた名前の属性がマネージド型に存在しない場合
     */
    Attribute<? super X, ?> getAttribute(String name); 

    /**
     *  Return the attribute declared by the managed
     *  type that corresponds to the specified name.
     *  @param name  表現された属性の名前
     *  @return 与えられた名前の属性
     *  @throws IllegalArgumentException 与えられた名前の属性がマネージド型で宣言されていない場合
     */
    Attribute<X, ?> getDeclaredAttribute(String name); 

    /**
     *  Return the single-valued attribute of the managed type that
     *  corresponds to the specified name.
     *  @param name  表現された属性の名前
     *  @return 与えられた名前の単一の値の属性
     *  @throws IllegalArgumentException 与えられた名前の属性がマネージド型に存在しない場合
     */
    SingularAttribute<? super X, ?> getSingularAttribute(String name);

    /**
     *  Return the single-valued attribute declared by the managed
     *  type that corresponds to the specified name.
     *  @param name  表現された属性の名前
     *  @return 与えられた名前の宣言された単一の値の属性
     *  @throws IllegalArgumentException 与えられた名前の属性がマネージド型で宣言されていない場合
     */
    SingularAttribute<X, ?> getDeclaredSingularAttribute(String name);

    /**
     *  Return the Collection-valued attribute of the managed type 
     *  that corresponds to the specified name.
     *  @param name  表現された属性の名前
     *  @return 与えられた名前のCollectionAttribute
     *  @throws IllegalArgumentException 与えられた名前の属性がマネージド型に存在しない場合
     */    
    CollectionAttribute<? super X, ?> getCollection(String name); 

    /**
     *  Return the Collection-valued attribute declared by the 
     *  managed type that corresponds to the specified name.
     *  @param name  表現された属性の名前
     *  @return 与えられた名前の宣言されたCollectionAttribute
     *  @throws IllegalArgumentException 与えられた名前の属性がマネージド型で宣言されていない場合
     */
    CollectionAttribute<X, ?> getDeclaredCollection(String name); 

    /**
     *  Return the Set-valued attribute of the managed type that
     *  corresponds to the specified name.
     *  @param name  表現された属性の名前
     *  @return 与えられた名前のSetAttribute
     *  @throws IllegalArgumentException 与えられた名前の属性がマネージド型に存在しない場合
     */
    SetAttribute<? super X, ?> getSet(String name);

    /**
     *  Return the Set-valued attribute declared by the managed type 
     *  that corresponds to the specified name.
     *  @param name  表現された属性の名前
     *  @return 与えられた名前の宣言されたSetAttribute
     *  @throws IllegalArgumentException 与えられた名前の属性がマネージド型で宣言されていない場合
     */
    SetAttribute<X, ?> getDeclaredSet(String name);

    /**
     *  Return the List-valued attribute of the managed type that
     *  corresponds to the specified name.
     *  @param name  表現された属性の名前
     *  @return 与えられた名前のListAttribute
     *  @throws IllegalArgumentException 与えられた名前の属性がマネージド型に存在しない場合
     */
    ListAttribute<? super X, ?> getList(String name);

    /**
     *  Return the List-valued attribute declared by the managed 
     *  type that corresponds to the specified name.
     *  @param name  表現された属性の名前
     *  @return 与えられた名前の宣言されたListAttribute
     *  @throws IllegalArgumentException 与えられた名前の属性がマネージド型で宣言されていない場合
     */
    ListAttribute<X, ?> getDeclaredList(String name);

    /**
     *  Return the Map-valued attribute of the managed type that
     *  corresponds to the specified name.
     *  @param name  表現された属性の名前
     *  @return 与えられた名前のMapAttribute
     *  @throws IllegalArgumentException 与えられた名前の属性がマネージド型に存在しない場合
     */
    MapAttribute<? super X, ?, ?> getMap(String name); 

    /**
     *  Return the Map-valued attribute declared by the managed 
     *  type that corresponds to the specified name.
     *  @param name  表現された属性の名前
     *  @return 与えられた名前の宣言されたMapAttribute
     *  @throws IllegalArgumentException 与えられた名前の属性がマネージド型で宣言されていない場合
     */
    MapAttribute<X, ?, ?> getDeclaredMap(String name);
}
