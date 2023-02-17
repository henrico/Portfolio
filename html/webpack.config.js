const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const { CleanWebpackPlugin } = require('clean-webpack-plugin');
const webpack = require('webpack');

module.exports = {
  mode: 'development',
  entry: {
    app: './src/index.js',
  },
  devtool: 'inline-source-map',
  devServer: {
    contentBase: './dist',
  },
  plugins: [
    new webpack.ProvidePlugin({
      $: 'jquery',
      jQuery: "jquery",
      ReactDOM:   'react-dom',
      React: 'react',
    }),
    new CleanWebpackPlugin(),
    new HtmlWebpackPlugin({
      title: 'Output Management',
    }),
  ],
  output: {
    filename: '[name].bundle.js',
    path: path.resolve(__dirname, 'dist'),
  },
  module: {
    rules: [
      {
        test: /\.css$/,
        use: [
          'style-loader',
          'css-loader',
        ],
      },
      {
        test: /\.(woff|woff2|eot|ttf|otf)$/,
        use: [
          'file-loader',
        ],
      },
      {
        test: /\.svg$/,
        loader: 'svg-inline-loader'
      },
      {
        test: /\.jsx?$/,
        exclude: /(node_modules)/,
        use: {
          loader: 'babel-loader',
          options: {
            presets: ['@babel/preset-env', '@babel/preset-react',{'plugins': ['@babel/plugin-proposal-class-properties']}]
          }
        }
      },
      {
        test : require.resolve('nprogress/nprogress.js'),
        use  : [
          {
            loader : 'imports-loader?define=>false'
          }
        ]

      },
        {
          test: /\.ico$/,
          loader: 'file-loader?name=[name].[ext]'
        }
    ],
  },
  devServer: {
    inline:true,
    port: 3000,
    proxy: {
           context: ['/'],
		    target: 'http://localhost:8080',
		    changeOrigin: true,
		    secure: false
        }
  }
};
