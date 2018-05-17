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
 * Java型の属性を表します。
 *
 * @param <X> 属性を含む表現された型
 * @param <Y> 表現された属性の型
 *
 * @since Java Persistence 2.0
 */
public interface Attribute<X, Y> {

	public static enum PersistentAttributeType {
	    
	     /** 多対1関連 */
	     MANY_TO_ONE, 

     	 /** 1対1関連 */
	     ONE_TO_ONE, 
	     
	     /** 基本属性 */
	     BASIC, 

	     /** 組み込み型属性 */
	     EMBEDDED,

	     /** 多対多関連 */
	     MANY_TO_MANY, 

	     /** 1対多関連 */
	     ONE_TO_MANY, 

	     /** 要素コレクション */
	     ELEMENT_COLLECTION
	}

    /**
     * 属性の名前を返します。
     * @return 名前
     */
    String getName();

    /**
     *  Return the persistent attribute type for the attribute.
     *  @return persistent attribute type
     */
    PersistentAttributeType getPersistentAttributeType();

    /**
     *  Return the managed type representing the type in which 
     *  the attribute was declared.
     *  @return declaring type
     */
    ManagedType<X> getDeclaringType();

    /**
     *  Return the Java type of the represented attribute.
     *  @return Java type
     */
    Class<Y> getJavaType();

    /**
     *  Return the <code>java.lang.reflect.Member</code> for the represented 
     *  attribute.
     *  @return corresponding <code>java.lang.reflect.Member</code>
     */
    java.lang.reflect.Member getJavaMember();

    /**
     *  Is the attribute an association.
     *  @return boolean indicating whether the attribute 
     *          corresponds to an association
     */
    boolean isAssociation();

    /**
     *  Is the attribute collection-valued (represents a Collection,
     *  Set, List, or Map).
     *  @return boolean indicating whether the attribute is 
     *          collection-valued
     */
    boolean isCollection();
}
