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

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * リストの永続化された順序を維持するために使用されるカラム(順序カラム)を指定します。
 * 
 * 永続化プロバイダは検索時とデータベース内での順序の維持を担当します。
 * 永続化プロバイダはリストに影響を与える挿入、削除、並べ替えを反映するために、
 * データベースへのフラッシュ時に順序の更新を担当します。
 *
 * <p> <code>OrderColumn</code>アノテーションは、OneToManyやManyToManyの関連、要素コレクションで指定されます。
 * <code>OrderColumn</code>アノテーションは関連のうち順序付けされるコレクションを参照する側で指定されます。
 * 順序カラムはエンティティや組み込みクラスの一部の状態としては参照できません。
 * 
 * <p> {@link OrderBy}アノテーションは永続化状態の一部として参照でき、アプリケーションによって維持される順序として使用される必要があります。
 * <code>OrderBy</code>アノテーションは<code>OrderColumn</code>が指定されている場合は使用しません。
 * 
 * <p> 順序カラムは整数型でなければいけません。
 * 永続化プロバイダは関連付けや要素コレクションを更新するときに順序カラムの値を連続した(疎ではない)順序を維持します。
 * 最初の要素の順序カラムの値は0です。
 *
 * <pre>
 *
 *    Example:
 *
 *    &#064;Entity
 *    public class CreditCard {
 *
 *       &#064;Id long ccNumber;
 *
 *       &#064;OneToMany  // unidirectional
 *       &#064;OrderColumn
 *       List&#060;CardTransaction&#062; transactionHistory;
 *       ...
 *    }
 *
 * </pre>
 *
 * @see OrderBy
 *
 * @since Java Persistence 2.0
 */
@Target( { METHOD, FIELD })
@Retention(RUNTIME)
public @interface OrderColumn {

	/** (オプション) 順序カラムの名前。
	 * 
	 * デフォルトでは参照もとのプロパティまたはフィールドの名前、"_"、"ORDER"が連結されます。
     */
	String name() default "";

	/** (オプション) データベースのカラムがNULLを許容するかどうか。 */
	boolean nullable() default true;

	/**
	 * (オプション) 永続化プロバイダによって生成されたSQL INSERTステートメントにカラムが含まれるかどうか。
	 */
	boolean insertable() default true;

	/**
	 * (オプション) 永続化プロバイダによって生成されたSQL UPDATEステートメントにカラムが含まれるかどうか。
	 */
	boolean updatable() default true;

	/**
	 * (オプション) カラムのDDLを生成するときに使用されるSQLフラグメント。
	 * 
	 * デフォルトでは型推論されたカラムを作成するSQLを生成します。
	 */
	String columnDefinition() default "";
}
