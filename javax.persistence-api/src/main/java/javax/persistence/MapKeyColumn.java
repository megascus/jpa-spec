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
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * マップキーが基本形であるマップのキー列のマッピングを指定します。
 * 
 * <code>name</code>要素が指定されていない場合、デフォルトでは次のものが連結されます。：参照元の関係フィールドまたはプロパティの名前 + "_" + "キー"
 * 
 * <pre>
 *    Example:
 *
 *    &#064;Entity
 *    public class Item {
 *       &#064;Id int id;
 *       ...
 *       &#064;ElementCollection
 *       &#064;MapKeyColumn(name="IMAGE_NAME")
 *       &#064;Column(name="IMAGE_FILENAME")
 *       &#064;CollectionTable(name="IMAGE_MAPPING")
 *       Map&#060;String, String&#062; images;  // map from image name to filename
 *       ...
 *    } 
 * </pre>
 * @since Java Persistence 2.0
 */
@Target( { METHOD, FIELD })
@Retention(RUNTIME)
public @interface MapKeyColumn {

	/**
	 * (オプション) マップキー列の名前。
	 * どのテーブルで見つけるかはコンテキストに依存します。  
	 * 要素コレクションのためのマップキーの場合、マップキー列はマップの値のコレクションテーブルに存在します。
     * ManyToManyのエンティティ関係や結合テーブルを使用したOneToManyのエンティティ関係のためのマップキーの場合、マップキーカラムは結合テーブルに存在します。
     * 外部キーマッピング戦略を使用したOneToManyのエンティティ関係のためのマップキーの場合、マップキー列は値がマッピングされたテーブルに存在します。
     * <p> デフォルトでは次のものが連結されます。：参照元の関係フィールドまたはプロパティの名前 + "_" + "キー"
	 */
	String name() default "";

	/**
	 * (オプション) このプロパティがユニークキーかどうか。
	 * 
	 * これはテーブルレベルの<code>UniqueConstraint</code>アノテーションのショートカットであり、
	 * ユニークキー制約が単一のフィールドだけである場合に便利です。
	 * この制約は主キーのマッピングとテーブルレベルで指定された制約に伴う制約に加えて適用されます。
	 */
	boolean unique() default false;

	/** (オプション) データベースのカラムがNULLを許容するかどうか。 */
	boolean nullable() default false;

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

	/** (オプション) この列が含まれるテーブルの名前。 
     *
     * <p> デフォルト：マップキーが要素コレクションの場合はマップ値のコレクションテーブルの名前。
	 * 結合テーブルを使用してマップキーがOneToManyまたはManyToManyエンティティの関係にある場合はマップの結合テーブルの名前。
	 * 外部キーマッピング戦略を使用してマップキーがOneToManyエンティティ関係用である場合はマップの値のエンティティのプライマリテーブルの名前。
     */
	String table() default "";

	/**
	 * (オプション) 列の長さ。(文字列値の列が使用されている場合のみ適用されます。)
	 */
	int length() default 255;

	/**
	 * (オプション) 10進数(正確な数値)の列の精度。 (10進数の列が使用されている場合にのみ適用されます。)
     *
     *<p> デフォルト: 0。 (値は開発者によって設定される必要があります。)
	 */
	int precision() default 0; // decimal precision

	/**
	 * (オプション) 10進数(正確な数値)の列の位取り。 (10進数の列が使用されている場合にのみ適用されます。)
	 */
	int scale() default 0; // decimal scale
}
