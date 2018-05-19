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
 * <code>Bindable</code>型のインスタンスは{@link javax.persistence.criteria.Path Path}にバインド可能なオブジェクトもしくは属性の型を表します。
 *
 * @param <T>  オブジェクトもしくは属性を表す型
 *
 * @since Java Persistence 2.0
 *
 */
public interface Bindable<T> {
	
	public static enum BindableType { 

	    /** 単一値の属性の型 */
	    SINGULAR_ATTRIBUTE, 

	    /** 複数値の属性の型 */
	    PLURAL_ATTRIBUTE, 

	    /** エンティティ型 */
	    ENTITY_TYPE
	}

    /**
     *  表されるオブジェクトのバインド可能な型を返します。
     *  @return バインド可能な型
     */	
    BindableType getBindableType();
	
    /**
     * 表されるオブジェクトのJava型を返します。
     * 
     * オブジェクトのバインド可能な型が<code>PLURAL_ATTRIBUTE</code>の場合、Java要素の型が返されます。
     * オブジェクトのバインド可能な型が<code>SINGULAR_ATTRIBUTE</code>もしくは<code>ENTITY_TYPE</code>の場合、エンティティや属性を表すJava型が返されます。
     * @return Java型
     */
    Class<T> getBindableJavaType();
}
