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

/**
 * <code>SingularAttribute</code>型のインスタンスは永続化された単一の値の値の属性を表します。
 *
 * @param <X> 表される属性を含む型
 * @param <T> 表される属性の型
 *
 * @since Java Persistence 2.0
 */
public interface SingularAttribute<X, T> 
		extends Attribute<X, T>, Bindable<T> {
	
    /**
     *  属性がID属性かどうか。
     * 
     *  このメソッドは、属性が単純なIDもしくは組み込みID、IDクラスの属性に対応する属性である場合にtrueを返します。
     *  @return 属性がIDかどうかを示すブール値
     */
    boolean isId();

    /**
     *  この属性がバージョン属性かどうか。
     *  @return 属性がバージョン属性かどうかを示すブール値
     */
    boolean isVersion();

    /** 
     *  この属性をnullにできるかどうか。
     *  @return 属性をnullにできるかどうかを示すブール値
     */
    boolean isOptional();

    /**
     * 属性の型を表す型を返します。
     * @return 属性の型
     */
    Type<T> getType();
}
