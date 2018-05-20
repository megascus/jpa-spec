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
 *  <code>IdentifiableType</code>型のインスタンスはエンティティもしくはマップドスーパークラスの型を表します。
 *
 *  @param <X> 表されるエンティティもしくはマップドスーパークラスの型
 *
 *  @since Java Persistence 2.0
 *
 */
public interface IdentifiableType<X> extends ManagedType<X> {
	
    /**
     *  エンティティまたはマップドスーパークラスのID属性に対応する属性を返します。
     *  @param type  表されたID属性の型
     *  @return ID属性
     *  @throws IllegalArgumentException 指定された型のID属性が識別可能な型に存在しない場合、または識別可能な型がIDクラスを持つ場合
     */
    <Y> SingularAttribute<? super X, Y> getId(Class<Y> type);

    /**
     *  エンティティまたはマップドスーパークラスによって宣言されたID属性に対応する属性を返します。
     *  @param type  表された宣言されたID属性の型
     *  @return 宣言されたID属性
     *  @throws IllegalArgumentException 指定された型のID属性が識別可能な型で宣言されていない場合、または識別可能な型がIDクラスを持つ場合
     */
    <Y> SingularAttribute<X, Y> getDeclaredId(Class<Y> type);

    /**
     *  エンティティまたはマップドスーパークラスのバージョン属性に対応する属性を返します。
     *  @param type  表されたバージョン属性の型
     *  @return バージョン属性
     *  @throws IllegalArgumentException 指定された型のバージョン属性が識別可能な型に存在しない場合
     */
    <Y> SingularAttribute<? super X, Y> getVersion(Class<Y> type);

    /**
     *  エンティティまたはマップドスーパークラスによって宣言されたバージョン属性に対応する属性を返します。
     *  @param type  表された宣言されたバージョン属性の型
     *  @return 宣言されたバージョン属性
     *  @throws IllegalArgumentException 型のバージョン属性が識別可能な型で宣言されていない場合
     */
    <Y> SingularAttribute<X, Y> getDeclaredVersion(Class<Y> type);
	
    /**
     *  このエンティティまたはマップドスーパークラスによって拡張された最も特化されたマップドスーパークラスまたはエンティティに対応する識別可能な型を返します。
     *  @return 識別可能型のスーパータイプ、スーパータイプがない場合はnull
     */
    IdentifiableType<? super X> getSupertype();

    /**
     *  識別可能型が単一のID属性を持つかどうか。
     * 
     *  単純なIDまたは組み込みIDの場合はtrueを返します。
     *  IDクラスの場合はfalseを返します。
     *  @return 識別可能な型が単一のID属性を持つかどうかを示すブール値
     */
    boolean hasSingleIdAttribute();

    /**
     *  識別可能型がバージョン属性を持っているかどうか。
     *  @return 識別可能型がバージョン属性を持っているかどうかを示すブール値
     */
    boolean hasVersionAttribute();

    /**
     *   識別可能型のIDクラスに対応する属性を返します。
     *   @return ID属性
     *   @throws IllegalArgumentException 識別可能型にIDクラスが存在しない場合
     */
     Set<SingularAttribute<? super X, ?>> getIdClassAttributes();

    /**
     *  IDの型を表す型を返します。
     *  @return IDの型
     */
    Type<?> getIdType();
}
